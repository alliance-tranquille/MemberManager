package be.shoktan.alliance.tranquille.config.util;

import be.shoktan.alliance.tranquille.model.discord.Member;
import be.shoktan.alliance.tranquille.model.discord.Role;
import be.shoktan.alliance.tranquille.service.DiscordService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static Logger LOGGER = Logger.getLogger(AuthenticationSuccessEventListener.class);

    private static final Role GUEST_ROLE = createGuestRole();

    private static Role createGuestRole() {
        Role role = new Role();
        role.setName("guest");
        role.setColor(0);
        return role;
    }


    private final DiscordService discordService;

    @Autowired
    public AuthenticationSuccessEventListener(DiscordService discordService) {
        this.discordService = discordService;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        LOGGER.info("event:: " + event.getClass());
        LOGGER.info("event source:: " + event.getSource().getClass());

        OAuth2Authentication auth = (OAuth2Authentication) event.getAuthentication();
        LOGGER.info("auth:: " + auth);

        Map<String, Object> details = (Map<String, Object>) auth.getUserAuthentication().getDetails();

        List<Member> members = discordService.getMembers();
        String id = details.get("id").toString();
        Member member = members.stream().filter(x -> StringUtils.equals(x.getUser().getId(), id)).findAny().orElse(null);
        details.put("member", member);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Map<String, Role> roles = discordService.getRoles();
        if (member != null) {
            for(String role : member.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(roles.get(role).getName()));
            }
        }

        Role mainRole = roles.values().stream().max(Comparator.comparing(Role::getPosition)).orElse(GUEST_ROLE);
        LOGGER.debug("mainRole is "+mainRole);
        details.put("mainRole", mainRole);

        details.put("roles", authorities);

        LOGGER.info("current user is: " + member);
    }
}
