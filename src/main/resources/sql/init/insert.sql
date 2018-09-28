INSERT INTO player(player_nickname, player_status, player_rating, player_min_rating, player_max_rating, hours_played)
    SELECT md5(random()::text), substring(md5(random()::text), 0, 30),  random() * 1000 + 2000, random() * 2000,
    random() * 2000 + 3000, random() * 100 FROM generate_series(1, 1000000);

INSERT INTO hero(hero_name, strength, agility, intelligence, min_power, max_power, attack_speed, attack_range,
                 movement_speed, health, mana, health_regen, mana_regen, armor)
    SELECT i, random() * 100, random() * 100, random() * 100, random() * 100, random() * 100,
    random() * 100, random() * 100, random() * 100, random() * 100, random() * 100, random() * 100, random() * 100,
    random() * 100 FROM generate_series(1, 100) AS i;

INSERT INTO spell(spell_name, description, hero_name)
    SELECT i, md5(random()::text), md5(random()::text) FROM generate_series(1, 400) AS i;

