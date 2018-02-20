package be.shoktan.alliance.tranquille.service.impl;

import be.shoktan.alliance.tranquille.model.User;
import be.shoktan.alliance.tranquille.repository.UserRepository;
import be.shoktan.alliance.tranquille.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = repository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("nom d'utilisateur inconnu : " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> result = new ArrayList<>();
        if(user.isAdmin()){
            result.add(new SimpleGrantedAuthority("admin"));
        }
        return result;
    }
}
