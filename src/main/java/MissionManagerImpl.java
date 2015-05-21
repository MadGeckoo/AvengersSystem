import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class MissionManagerImpl implements MissionManager {

	private SuperHeroManager superHeroManager;
	private VillainManager villainManager;
    private JdbcTemplate jdbc;

    final static Logger log = LoggerFactory.getLogger(MissionManagerImpl.class);


    public MissionManagerImpl(DataSource dataSource)
    {
        jdbc=new JdbcTemplate(dataSource);
    }

    public void setSuperHeroManager(SuperHeroManager superHeroManager) {
        this.superHeroManager = superHeroManager;
    }

    public void setVillainManager(VillainManager villainManager) {
        this.villainManager = villainManager;
    }

    /**
	 * 
	 * @param mission
	 */
    @Override
    @Transactional
	public void createMission(Mission mission) throws IllegalEntityException
    {
        validateMission(mission);
		SimpleJdbcInsert insertMission=new SimpleJdbcInsert(jdbc).withTableName("missions").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters=new MapSqlParameterSource()
                .addValue("heroid",mission.getHero().getId())
                .addValue("villainid", mission.getVillain().getId())
                .addValue("location",mission.getLocation())
                .addValue("date",toSQLDate(mission.getDate()))
                .addValue("herowon",mission.getHeroWon());
        Number id=insertMission.executeAndReturnKey(parameters);
        mission.setId(id.longValue());
        log.debug("created {}",mission);

    }

    @Override
    @Transactional
	public List<Mission> findAllMissions()
    {
        log.debug("started fetching all missions");
        return jdbc.query("SELECT * FROM missions",
                (rs,rowNum)->{
                    long id = rs.getLong("id");
                    String location=rs.getString("location");
                    LocalDate date=rs.getDate("date").toLocalDate();
                    SuperHero hero=superHeroManager.getHeroByID(rs.getLong("heroid"));
                    Villain villain=villainManager.getVillainByID(rs.getLong("villainid"));
                    boolean herowon=rs.getBoolean("herowon");
                    return new Mission(id,location,date,hero,villain,herowon);
                });

	}

	/**
	 * 
	 * @param superHero
	 */
    @Override
	public List<Mission> findMissionsByHero(SuperHero superHero) throws IllegalEntityException{
        validateHero(superHero);
        log.debug("started fetching missions for hero {}", superHero);
        return jdbc.query("SELECT * FROM missions WHERE heroid=?",
                (rs,rowNum)->{
                    long villainId=rs.getLong("villainid");
                    Villain villain=null;
                    try{
                        villain=villainManager.getVillainByID(villainId);
                    } catch (Exception e)
                    {}
                    String location= rs.getString("location");
                    LocalDate date=rs.getDate("date").toLocalDate();
                    boolean herowon=rs.getBoolean("herowon");
                    return new Mission(rs.getLong("id"),location, date,superHero,villain,herowon);
                },superHero.getId());
	}

	/**
	 * 
	 * @param villain
	 */
    @Override
	public List<Mission> findMissionsByVillain(Villain villain) throws IllegalEntityException {
        validateVillain(villain);
        log.debug("started fetching missions for villain {}", villain);
        return jdbc.query("SELECT * FROM missions WHERE villainid=?",
                (rs, rowNum) -> {
                    long heroId = rs.getLong("heroid");
                    SuperHero hero = null;
                    try {
                        hero = superHeroManager.getHeroByID(heroId);
                    } catch (Exception e) {
                    }
                    String location = rs.getString("location");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    boolean herowon = rs.getBoolean("herowon");
                    return new Mission(rs.getLong("id"), location, date, hero, villain, herowon);
                }, villain.getId());
	}

	/**
	 * 
	 * @param id
	 */
    @Override
	public Mission getMissionByID(Long id) {
        log.debug("started fetching mission with id {}", id);
        return jdbc.queryForObject("SELECT * FROM missions WHERE id=?",
                (rs,rowNum)->{
                    String location=rs.getString("location");
                    LocalDate date=rs.getDate("date").toLocalDate();
                    SuperHero hero=superHeroManager.getHeroByID(rs.getLong("heroid"));
                    Villain villain=villainManager.getVillainByID(rs.getLong("villainid"));
                    boolean herowon=rs.getBoolean("herowon");
                    return new Mission(id,location,date,hero,villain,herowon);
                },id);
    }

	/**
	 * 
	 * @param mission
	 */
    @Transactional
    @Override
	public void updateMission(Mission mission) throws IllegalEntityException {
        validateMission(mission);
		jdbc.update("UPDATE missions SET location=?,date=?,heroid=?,villainid=?,herowon=? WHERE id=?",mission.getLocation(),toSQLDate(mission.getDate()),mission.getHero().getId(),mission.getVillain().getId(),mission.getHeroWon(),mission.getId());
        log.debug("updated mission {}", mission);
	}


    private Date toSQLDate(LocalDate localDate) {
        if (localDate == null) return null;
        return new Date(ZonedDateTime.of(localDate.atStartOfDay(), ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    private void validateHero(SuperHero hero) throws IllegalEntityException {
        if (hero==null)
        {
            log.error(" hero is null ");
            throw new IllegalEntityException("hero is null");
        }

        if (hero.getSuperName()==null || hero.getSuperName().equals(""))
        {
            log.error("hero doesnt have super name");
            throw new IllegalEntityException("hero with no hero name");
        }

    }

    private void validateVillain(Villain villain) throws IllegalEntityException {
        if (villain==null)
        {
            log.error(" villain is null ");
            throw new IllegalEntityException("villain is null");
        }

        if (villain.getVillainName()==null || villain.getVillainName().equals(""))
        {
            log.error("villain doesnt have villain name");
            throw new IllegalEntityException("villain with no villain name");
        }

    }


    private void validateMission(Mission mission) {
        if (mission == null) {
            log.error(" mission is null ");
            throw new IllegalArgumentException("The mission was null.");
        }

        if (mission.getLocation().equals("") || mission.getLocation()==null)
        {
            log.error("mission doesnt have location");
            throw new IllegalStateException("Mission without location");
        }
    }


}