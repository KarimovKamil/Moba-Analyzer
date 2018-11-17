DROP TABLE IF EXISTS hero;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS match;
DROP TABLE IF EXISTS pick;

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
  dire_win BOOLEAN,
  average_rating INT,
  start_time BIGINT,
  match_duration BIGINT,
  PRIMARY KEY (match_id)
);

CREATE TABLE pick (
  pick_id BIGSERIAL,
  player_id BIGINT,
  hero_name VARCHAR,
  dire BOOLEAN,
  net_worth INT,
  gpm INT,
  xpm INT,
  kills INT,
  assissts INT,
  deaths INT,
  total_damage INT,
  total_heal INT,
  total_damage_taken INT,
  wards_placed INT,
  team_gold INT,
  PRIMARY KEY (pick_id)
);