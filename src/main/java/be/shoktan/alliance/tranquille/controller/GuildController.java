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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @RequestMapping("/discord")
    public String discordMembers(Model model){
        List<be.shoktan.alliance.tranquille.model.discord.Member> members = discordService.getMembers();
        model.addAttribute("members", members);

        List<Role> roles = discordService.getRoles();
        Map<String, Role> collect = roles.stream().collect(Collectors.toMap(x -> x.getId().toString(), x -> x));
        LOGGER.info(String.format("searching %s in map:: %s", "416629300373094410", collect.get("416629300373094410").getName()));
        model.addAttribute("roles", collect);
        return "discordList";
    }


    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String logs(Model model, @RequestParam("sort") Optional<GuildLogEventSort> sort, @RequestParam("rev") Optional<Boolean> reverse, @RequestParam("user") Optional<String> user, Principal principal) {
        /*OAuth2Authentication auth = (OAuth2Authentication) principal;
        LOGGER.info("auth details:: " + auth.getUserAuthentication().getDetails());
        LOGGER.info("details:: " + auth.getDetails());
        LOGGER.info("auth details type:: " + auth.getUserAuthentication().getDetails().getClass());
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        User discord = discordService.getUserDetail(details.getTokenValue());*/
        discordService.getMembers();


        List<GuildLogEvent> datas = guildLogEventService.findAll();
        datas.removeIf(
                e -> (e.getType() == GuildLogEventType.treasury
                        || e.getType() == GuildLogEventType.motd
                        || e.getType() == GuildLogEventType.influence
                        || e.getType() == GuildLogEventType.stash
                        || e.getType() == GuildLogEventType.upgrade)
        );

        user.ifPresent(s -> datas.removeIf(x -> !StringUtils.equalsIgnoreCase(x.getUser(), s)));

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
