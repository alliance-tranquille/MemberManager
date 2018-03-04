insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami Crachh, Otah et Wisthler', id from guild_log_event where in_game_id = 1084151;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami de keiser.9256 (draynel)', id from guild_log_event where in_game_id = 1083605;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, '3ème compte wisthler.6879', id from guild_log_event where in_game_id = 1083026;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ami Lysgard.1862', id from guild_log_event where in_game_id = 1083225;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'débutant', id from guild_log_event where in_game_id = 1083605;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'fausse manip', id from guild_log_event where in_game_id = 1083022;
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'post sur le forum "expliquant" le départ : Suite a quelques points que je comprend pas je préfére vous quitter que de me prendre la tête, bonne continuation a vous.', id from guild_log_event where in_game_id = 1083676;


insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'second compte Axen', id from guild_log_event where in_game_id = 1084651;



insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 12, '2018-02-21 08:30:01', 'Skinou.4851', 0, 0, 'Magni');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 13, '2018-02-21 08:58:01', 'Skinou.4851', 0, 0, null);
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-22 09:24:01', 'Skinou.4851', 0, 0, 'Magni#8436');

insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-22 17:32:01', 'Stray.6312', 0, 0, 'Stray#1012');


insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 12, '2018-02-26 06:15:01', 'Astarock.1578', 0, 0, 'Astarock');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 13, '2018-02-26 07:07:01', 'Astarock.1578', 0, 0, null);


insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 12, '2018-02-27 09:49:01', 'Pikinne.3815', 0, 0, 'Pikinne');
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 13, '2018-02-27 10:52:01', 'Pikinne.3815', 0, 0, null);


--Jerem.3553 	Jerem.3553 	[Membre] 	2018-02-19T17:26:47.040
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-19 17:26:47', 'code secret.8219', 0, 0, 'Jerem.3553');
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'ré-invité après kick', max(id) from guild_log_event;


--Cypher974.4862 	Cypher974.4862 	[Recrue] 	2018-02-24T22:39:33.489
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-24 22:39:33', 'Fred.6285', 0, 0, 'Cypher974.4862');

--Evalla.3135 	Evalla.3135 	[Membre] 	2018-02-26T18:41:49.091
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-26 18:41:49', 'Evalla.9143', 0, 0, 'Evalla.3135');


--Astarock.7367 	Astarock.7367 	[Recrue] 	2018-02-26T19:54:13.052
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-26 19:54:13', 'Astarock.1578', 0, 0, 'Astarock.7367');


--Misstique.5142 	Misstique.5142 	[Membre] 	2018-02-27T22:51:27.400
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-27 22:51:27', 'misstique.2509', 0, 0, 'Misstique.5142');


--Pikinne.4282
insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 11, '2018-02-28 19:46:13', 'Pikinne.3815', 0, 0, 'Pikinne.4282');



insert into guild_log_event (id, type, time, target_user, coins, in_game_id, action) values (nextval('hibernate_sequence'), 12, '2018-02-25 07:11:01', 'Klakius.????', 0, 0, 'Klakius');
insert into guild_log_comment (id, author, content, event) select nextval('hibernate_sequence'), 1, 'inscrit depuis une semaine sans avoir posté. Je lui ai envoyé un mp', max(id) from guild_log_event;