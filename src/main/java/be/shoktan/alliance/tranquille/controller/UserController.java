package be.shoktan.alliance.tranquille.controller;

import be.shoktan.alliance.tranquille.model.User;
import be.shoktan.alliance.tranquille.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepository repository;

    private final PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @RequestMapping("")
    public String home(){
        return "redirect:/user/";
    }

    @RequestMapping("/")
    public String list(Model model){
        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("newUser", new User());
        return "userList";
    }

    @RequestMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable Long id){
        User user = repository.findOne(id);
        model.addAttribute("user", user);
        return "userShow";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute User user){
        if(StringUtils.isBlank(user.getPassword())){
            user.setPassword(encoder.encode(UUID.randomUUID().toString()));
        }
        repository.save(user);
        return home();
    }
}
