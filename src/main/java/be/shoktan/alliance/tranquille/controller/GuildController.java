package be.shoktan.alliance.tranquille.controller;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.GuildLogEventType;
import be.shoktan.alliance.tranquille.model.Member;
import be.shoktan.alliance.tranquille.model.discord.Role;
import be.shoktan.alliance.tranquille.model.discord.User;
import be.shoktan.alliance.tranquille.service.DiscordService;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import be.shoktan.alliance.tranquille.service.MemberService;
import be.shoktan.alliance.tranquille.service.impl.DiscordServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/guild")
public class GuildController {
    private static final Logger LOGGER = Logger.getLogger(GuildController.class);

    private final MemberService memberService;

    private final GuildLogEventService guildLogEventService;

    private final DiscordService discordService;

    @Autowired
    public GuildController(MemberService memberService, GuildLogEventService guildLogEventService, DiscordService discordService) {
        this.memberService = memberService;
        this.guildLogEventService = guildLogEventService;
        this.discordService = discordService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Member> members = memberService.findAll();

        model.addAttribute("members", members);

        return "guildMembers";
    }

    @RequestMapping("")
    public String home(){
        return "redirect:/guild/";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/discord")
    public String discordMembers(Model model, @RequestParam("sort") Optional<String> sortingElement, @RequestParam("reverse") Optional<Boolean> reverseSorting){
        String sorting = sortingElement.orElse("");
        boolean reverse = reverseSorting.orElse(false);

        Comparator<be.shoktan.alliance.tranquille.model.discord.Member> comparator;
        switch (sorting){
            case "account": comparator = Comparator.comparing(x -> x.getUser().getAccountName()); break;
            case "joinDate":comparator = Comparator.comparing(be.shoktan.alliance.tranquille.model.discord.Member::getJoinedAt); break;
            case "surname":
            default: comparator = Comparator.comparing(be.shoktan.alliance.tranquille.model.discord.Member::getDisplayName);
        }

        if(reverse){
            comparator = comparator.reversed();
        }

        List<be.shoktan.alliance.tranquille.model.discord.Member> members = discordService.getMembers();
        if(comparator != null){
            members.sort(comparator);
        }

        model.addAttribute("members", members);

        Map<String, Role> roles = discordService.getRoles();
        //Map<String, Role> collect = roles.stream().collect(Collectors.toMap(x -> x.getId().toString(), x -> x));
        model.addAttribute("roles", roles);
        return "discordList";
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String logs(Model model, @RequestParam("sort") Optional<GuildLogEventSort> sort, @RequestParam("rev") Optional<Boolean> reverse, @RequestParam("user") Optional<String> user, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @RequestParam("since") Optional<LocalDateTime> since) {
        List<GuildLogEvent> datas = guildLogEventService.findAll();
        datas.removeIf(
                e -> (e.getType() == GuildLogEventType.treasury
                        || e.getType() == GuildLogEventType.motd
                        || e.getType() == GuildLogEventType.influence
                        || e.getType() == GuildLogEventType.stash
                        || e.getType() == GuildLogEventType.upgrade)
        );

        user.ifPresent(s -> datas.removeIf(x -> !StringUtils.equalsIgnoreCase(x.getUser(), s)));

        since.ifPresent(
                s -> datas.removeIf(x -> x.getTime().isBefore(s))
        );

        boolean reverseOrder = reverse.orElse(true);
        GuildLogEventSort sorter = sort.orElse(GuildLogEventSort.time);


        Comparator<GuildLogEvent> comparator;
        switch (sorter) {
            case id:
                comparator = Comparator.comparing(GuildLogEvent::getId);
                break;
            case time:
                comparator = Comparator.comparing(GuildLogEvent::getTime);
                break;
            case name:
            default:
                comparator = Comparator.comparing(GuildLogEvent::getUser);
        }


        if (reverseOrder) {
            datas.sort(comparator.reversed());
        } else {
            datas.sort(comparator);
        }


        model.addAttribute("logs", datas);

        return "guildLogs";
    }

    public enum GuildLogEventSort {
        id, name, time
    }
}
