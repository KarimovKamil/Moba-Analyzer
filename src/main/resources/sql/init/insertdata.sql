DO $$
BEGIN
  FOR i IN 1..100000 LOOP
    INSERT INTO match (data) VALUES (('{"averageRating":' || trunc(random() * 6000 + 500) :: TEXT || ', "startTime":' ||
                                      trunc(random() * 1000000000 + 1537555019572) :: TEXT || ', "matchDuration":' ||
                                      trunc(random() * 10800 + 0) :: TEXT || ',
                                "picks":' || '[{"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":'
                                      || trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
{"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' || trunc(random() * 99 + 1) :: TEXT ||
                                      ', "netWorth":' || trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '},
                                {"playedId":' || trunc(random() * 10000 + 1) :: TEXT || ', "heroName":' ||
                                      trunc(random() * 99 + 1) :: TEXT || ', "netWorth":' ||
                                      trunc(random() * 60000 + 600) :: TEXT || ', "GPM":' ||
                                      trunc(random() * 1500 + 300) :: TEXT || ',
                                "XPM":' || trunc(random() * 9 + 1) :: TEXT || ', "kills":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "assists":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "deaths":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "totalDamage":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "totalHeal":' || trunc(random() * 9 + 1) :: TEXT || ', "totalDamageTaken":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ', "wardsPlaced":' ||
                                      trunc(random() * 9 + 1) :: TEXT || ',
                                "teamGold":' || trunc(random() * 9 + 1) :: TEXT || '}]}') :: JSONB);
  END LOOP;
END;
$$
