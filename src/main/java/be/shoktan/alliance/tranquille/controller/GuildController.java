package be.shoktan.alliance.tranquille.controller;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.Member;
import be.shoktan.alliance.tranquille.service.GuildLogEventService;
import be.shoktan.alliance.tranquille.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping("/guild/log")
    public String logs(Model model) {
        List<GuildLogEvent> datas = guildLogEventService.findAll();

        model.addAttribute("logs", datas);

        return "guildLogs";
    }
}
