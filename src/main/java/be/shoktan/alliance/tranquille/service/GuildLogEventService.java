package be.shoktan.alliance.tranquille.service;

import be.shoktan.alliance.tranquille.model.GuildLogEvent;
import be.shoktan.alliance.tranquille.model.Member;

import java.util.List;

public interface GuildLogEventService {
    List<GuildLogEvent> findAll();
}
