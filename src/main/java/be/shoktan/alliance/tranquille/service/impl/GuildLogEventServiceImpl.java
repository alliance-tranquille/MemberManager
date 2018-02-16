package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.Member;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static be.shoktan.alliance.tranquille.Application.API_KEY;
import static be.shoktan.alliance.tranquille.Application.GUILD_ID;

@Service
public class GuildLogEventServiceImpl implements GuildLogEventService {
    private List<GuildLogEvent> datas;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<GuildLogEvent> findAll() {
        if(datas == null){
            ResponseEntity<GuildLogEvent[]> response = restTemplate.getForEntity(
                    String.format("https://api.guildwars2.com/v2/guild/%s/log?access_token=%s", GUILD_ID, API_KEY),
                    GuildLogEvent[].class
            );
            GuildLogEvent[] data = response.getBody();
            datas = Arrays.asList(data);
        }
        return datas;
    }
}
