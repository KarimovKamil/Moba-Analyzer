SELECT *
FROM match
WHERE data @> ('{"picks":[{"playedId":' || random() * 1000000 || '}]}') :: JSONB;