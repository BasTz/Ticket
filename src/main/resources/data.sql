INSERT INTO "movie" ("name") VALUES ('MOVIE-1');
INSERT INTO "movie" ("name") VALUES ('MOVIE-2');
INSERT INTO "movie" ("name") VALUES ('MOVIE-3');
INSERT INTO "movie" ("name") VALUES ('MOVIE-4');
INSERT INTO "movie" ("name") VALUES ('MOVIE-5');
INSERT INTO "movie" ("name") VALUES ('MOVIE-6');
COMMIT;

INSERT INTO "theater" ("name") VALUES ('THEATER-1');
INSERT INTO "theater" ("name") VALUES ('THEATER-2');
INSERT INTO "theater" ("name") VALUES ('THEATER-3');
INSERT INTO "theater" ("name") VALUES ('THEATER-4');
INSERT INTO "theater" ("name") VALUES ('THEATER-5');
INSERT INTO "theater" ("name") VALUES ('THEATER-6');
COMMIT;

INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,1,'2022-07-10T20:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (2,2,'2022-07-10T20:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,2,'2022-07-10T11:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,2,'2022-07-10T17:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,1,'2022-07-13T20:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (2,2,'2022-07-13T20:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,2,'2022-07-13T11:00:00.000+07:00');
INSERT INTO "showtime" ("theater_id","movie_id","datetime") VALUES (1,2,'2022-07-13T17:00:00.000+07:00');
COMMIT;