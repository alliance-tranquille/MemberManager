package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.GuildLogEventType;
import be.shoktan.alliance.tranquille.repository.GuildLogEventRepository;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static be.shoktan.alliance.tranquille.Application.API_KEY;
import static be.shoktan.alliance.tranquille.Application.GUILD_ID;

@Service
public class GuildLogEventServiceImpl implements GuildLogEventService {

    private final GuildLogEventRepository repository;

    private final RestTemplate restTemplate;

    @Autowired
    public GuildLogEventServiceImpl(RestTemplate restTemplate, GuildLogEventRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @Override
    public List<GuildLogEvent> findAll() {
        reload();
        return repository.findAll();
    }

    @Override
    public void reload() {
        String request = String.format("https://api.guildwars2.com/v2/guild/%s/log?access_token=%s", GUILD_ID, API_KEY);
        Long lastId = repository.getMaxInGameId();
        if (lastId != null) {
            request += "&since=" + lastId;
        }

        ResponseEntity<GuildLogEvent[]> response = restTemplate.getForEntity(request, GuildLogEvent[].class);
        GuildLogEvent[] data = response.getBody();

        List<GuildLogEvent> entities = new ArrayList<>();
        entities.addAll(Arrays.asList(data));
        //entities.removeIf(x -> GuildLogEventType.motd.equals(x.getType()));
        Stream<GuildLogEvent> stream = entities.stream();
        stream.filter(x -> !GuildLogEventType.motd.equals(x.getType()))
                .map(this::clean)
                .forEach(repository::save);
        //repository.save(entities);
    }

    private GuildLogEvent clean(GuildLogEvent guildLogEvent) {
        if(guildLogEvent != null
                && GuildLogEventType.kick.equals(guildLogEvent.getType())
                && Objects.equals(guildLogEvent.getActor(), guildLogEvent.getUser())
                ){
            guildLogEvent.setType(GuildLogEventType.left);
        }
        return guildLogEvent;
    }
}
