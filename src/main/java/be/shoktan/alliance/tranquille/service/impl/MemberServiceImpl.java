package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.Member;
import be.shoktan.alliance.tranquille.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static be.shoktan.alliance.tranquille.Application.API_KEY;
import static be.shoktan.alliance.tranquille.Application.GUILD_ID;

@Service
public class MemberServiceImpl implements MemberService{
    private List<Member> members;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Member> findAll() {
        if(members == null){
            ResponseEntity<Member[]> response = restTemplate.getForEntity(
                    String.format("https://api.guildwars2.com/v2/guild/%s/members?access_token=%s", GUILD_ID, API_KEY),
                    Member[].class
            );
            Member[] data = response.getBody();
            members = Arrays.asList(data);
        }
        return members;
    }
}
