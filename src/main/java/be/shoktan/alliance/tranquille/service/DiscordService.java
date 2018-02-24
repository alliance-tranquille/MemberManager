package be.shoktan.alliance.tranquille.service;

import be.shoktan.alliance.tranquille.model.discord.Member;
import be.shoktan.alliance.tranquille.model.discord.Role;
import be.shoktan.alliance.tranquille.model.discord.User;

import java.util.List;
import java.util.Map;

public interface DiscordService {
    User getUserDetail(String token);

    List<Member> getMembers();

    Map<String, Role> getRoles();
}
