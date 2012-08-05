drop view v_rankings;
create view v_rankings as select sum(mt.points) AS points,
        t.lib_team AS lib_team,
        sum(mt.victory) AS victory,
        sum(mt.defeat) AS defeat,
        sum(mt.draw) AS draw,
        sum(goal_scored) as goal_scored,
        sum(goal_shipped) as goal_shipped,
        mt.id_team as id_team,
        t.id_club as id_club,
        m.id_season_competition
  from fam_match_team mt,
          fam_match m,
          fam_team t
  where mt.id_match=m.idMatch
          and t.id_team=mt.id_team
        -- and m.id_season_competition=2
  group by m.id_season_competition,mt.id_team ;
