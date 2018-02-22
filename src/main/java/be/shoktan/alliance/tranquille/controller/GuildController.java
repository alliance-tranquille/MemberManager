package be.shoktan.alliance.tranquille.controller;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.GuildLogEventType;
import be.shoktan.alliance.tranquille.model.Member;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import be.shoktan.alliance.tranquille.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class GuildController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GuildLogEventService guildLogEventService;

    @RequestMapping("/guild")
    public String index(Model model) {
        List<Member> members = memberService.findAll();

        model.addAttribute("members", members);

        return "guildMembers";
    }


    @RequestMapping(value = "/guild/log", method = RequestMethod.GET)
    public String logs(Model model, @RequestParam("sort") Optional<GuildLogEventSort> sort, @RequestParam("rev") Optional<Boolean> reverse, @RequestParam("user") Optional<String> user) {
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
