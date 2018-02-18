package be.shoktan.alliance.tranquille.repository;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuildLogEventRepository extends CrudRepository<GuildLogEvent, Long> {

    @Query("SELECT max(ch.inGameId) FROM GuildLogEvent ch")
    Long getMaxInGameId();


    List<GuildLogEvent> findAll();
}
