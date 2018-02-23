package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.discord.User;
import be.shoktan.alliance.tranquille.service.DiscordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordServiceImpl implements DiscordService {
    private static final Logger LOGGER = Logger.getLogger(DiscordServiceImpl.class);

    private final RestTemplate restTemplate;

    private static final String baseUrl = "https://discordapp.com/api";
    public static final String CLIENT_ID = System.getenv().get("CLIENT_ID");

    @Autowired
    public DiscordServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public User getUserDetail(){
        String request = String.format("%s/users/@me?%s", baseUrl, CLIENT_ID);
        ResponseEntity<User> response = restTemplate.getForEntity(request, User.class);
        User user = response.getBody();
        LOGGER.info(String.format("retrieved data: %s", user));
        return user;
    }

}
