package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static be.shoktan.alliance.tranquille.Application.API_KEY;
import static be.shoktan.alliance.tranquille.Application.GUILD_ID;

@Service
public class GuildLogEventServiceImpl implements GuildLogEventService {
    private List<GuildLogEvent> datas = new ArrayList<>();
    private int lastId = -1;

    private final RestTemplate restTemplate;

    @Autowired
    public GuildLogEventServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GuildLogEvent> findAll() {
        String request = String.format("https://api.guildwars2.com/v2/guild/%s/log?access_token=%s", GUILD_ID, API_KEY);
        if(lastId >0) {
            request += "&since="+lastId;
        }

        ResponseEntity<GuildLogEvent[]> response = restTemplate.getForEntity(request, GuildLogEvent[].class);
        GuildLogEvent[] data = response.getBody();
        if(data != null && data.length > 0 && data[0] != null){
            lastId = data[0].getId();
        }
        datas.addAll(0, Arrays.asList(data));

        return new ArrayList<>(datas);
    }
}
