DROP TABLE IF EXISTS spell;
DROP TABLE IF EXISTS hero;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS match;

CREATE TABLE spell (
  spell_name  VARCHAR,
  description VARCHAR,
  hero_name   VARCHAR,
  PRIMARY KEY (spell_name)
);

CREATE TABLE hero (
  hero_name      VARCHAR,
  strength       INT,
  agility        INT,
  intelligence   INT,
  min_power      INT,
  max_power      INT,
  attack_speed   INT,
  attack_range   INT,
  movement_speed INT,
  health         INT,
  mana           INT,
  health_regen   FLOAT,
  mana_regen     FLOAT,
  armor          FLOAT,   
  PRIMARY KEY (hero_name)
);

CREATE TABLE player (
  player_id         BIGSERIAL,
  player_nickname   VARCHAR(50),
  player_status     VARCHAR(30),
  player_rating     INT,
  player_min_rating INT,
  player_max_rating INT,
  hours_played      FLOAT,
  PRIMARY KEY (player_id)
);

CREATE TABLE match (
  match_id BIGSERIAL,
  data     JSONB,
  PRIMARY KEY (match_id)
);