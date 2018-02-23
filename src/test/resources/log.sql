insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami Crachh, Otah et Wisthler', id from guild_log_event where in_game_id = 1084151;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami de keiser.9256 (draynel)', id from guild_log_event where in_game_id = 1083605;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, '3ème compte wisthler.6879', id from guild_log_event where in_game_id = 1083026;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami Lysgard.1862', id from guild_log_event where in_game_id = 1083225;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'débutant', id from guild_log_event where in_game_id = 1083605;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'fausse manip', id from guild_log_event where in_game_id = 1083022;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'post sur le forum "expliquant" le départ : Suite a quelques points que je comprend pas je préfére vous quitter que de me prendre la tête, bonne continuation a vous.', id from guild_log_event where in_game_id = 1083676;


insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-22 09:24:01', 'Skinou.4851', 0, 0, 'Magni#8436');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-22 17:32:01', 'Stray.6312', 0, 0, 'Stray#1012');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 12, '2018-02-21 08:30:01', 'Skinou.4851', 0, 0, 'Magni');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 13, '2018-02-21 08:58:01', 'Skinou.4851', 0, 0, null);
