package be.shoktan.alliance.tranquille.controller;

import be.shoktan.alliance.tranquille.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GuildController {

    @RequestMapping("/guild")
    public String index(Model model) {
        List<Member> members = new ArrayList<>();
        members.add(new Member("wisthler", "star", "2015-07-22T06:18:35.000Z"));
        members.add(new Member("shoktan", "pouet", "2015-06-17T02:38:27.000Z"));

        model.addAttribute(members);

        return "guildMembers";
    }

}
