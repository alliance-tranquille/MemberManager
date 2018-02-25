package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.discord.Member;
import be.shoktan.alliance.tranquille.model.discord.Role;
import be.shoktan.alliance.tranquille.model.discord.User;
import be.shoktan.alliance.tranquille.service.DiscordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DiscordServiceImpl implements DiscordService {
    private static final Logger LOGGER = Logger.getLogger(DiscordServiceImpl.class);
    public static final int TIME_LIMIT = 5;

    private final RestTemplate restTemplate;

    private static final String baseUrl = "https://discordapp.com/api";

    @Value("${discord.botToken}")
    private String botToken;


    @Value("${discord.guildId}")
    private String guildId;

    private List<Member> members;
    private Instant memberRefresh;

    private Map<String, Role> roles;
    private Instant rolesRefresh;

    @Autowired
    public DiscordServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpEntity<String> getHeaders(String token) {
        return getHeaders(true, token);
    }

    private HttpEntity<String> getHeaders(boolean bearer, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", (bearer ? "Bearer" : "Bot") + " " + token);
        headers.add("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        headers.add("Connection", "keep-alive");
        return new HttpEntity<>("", headers);
    }


    @Override
    public User getUserDetail(String token) {
        String request = String.format("%s/users/@me", baseUrl);

        LOGGER.info(String.format("going to call %s with token <%s>", request, token));


        ResponseEntity<User> response = restTemplate.exchange(request, HttpMethod.GET, getHeaders(token), User.class);
        User user = response.getBody();
        LOGGER.info(String.format("retrieved data: %s", user));
        return user;
    }


    @Override
    public List<Member> getMembers() {
        boolean toCall;
        toCall = checkCacheTime(memberRefresh);
        if(toCall){
            String request = String.format("%s/guilds/%s/members?limit=%d", baseUrl, guildId, 1000);
            HttpEntity<String> botHeader = getHeaders(false, botToken);
            LOGGER.info(String.format("going to call %s with token <%s> (header: %s)", request, botToken, botHeader.getHeaders().get("Authorization")));
            ResponseEntity<Member[]> response = restTemplate.exchange(request, HttpMethod.GET, botHeader, Member[].class);
            members = Arrays.asList(response.getBody());
            LOGGER.info(String.format("retrieved data: %s", members));
            memberRefresh = Instant.now();
        }
        List<Member> result = new ArrayList<>();
        result.addAll(members);
        return result;
    }

    private boolean checkCacheTime(Instant reference) {
        boolean toCall;
        if(reference == null) {
            toCall = true;
        }else{
            Instant now = Instant.now();
            Duration duration = Duration.between(reference, now);
            long seconds = duration.getSeconds();
            long limit = TimeUnit.MINUTES.toSeconds(TIME_LIMIT);
            toCall = (seconds > limit);
        }
        return toCall;
    }

    @Override
    public Map<String, Role> getRoles() {
        boolean toCall;
        toCall = checkCacheTime(rolesRefresh);
        if(toCall) {
            String request = String.format("%s/guilds/%s/roles", baseUrl, guildId);
            HttpEntity<String> botHeader = getHeaders(false, botToken);
            LOGGER.info(String.format("going to call %s with token <%s> (header: %s)", request, botToken, botHeader.getHeaders().get("Authorization")));
            ResponseEntity<Role[]> response = restTemplate.exchange(request, HttpMethod.GET, botHeader, Role[].class);
            roles = Arrays.stream(response.getBody()).collect(Collectors.toMap(x -> x.getId().toString(), x -> x));
            LOGGER.info(String.format("retrieved data: %s", roles));
            rolesRefresh = Instant.now();
        }
        return new HashMap<>(roles);
    }
}
