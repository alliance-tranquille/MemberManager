package be.shoktan.alliance.tranquille.repository;

import be.shoktan.alliance.tranquille.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
    List<User> findAll();
}
