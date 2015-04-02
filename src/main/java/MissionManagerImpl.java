import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class MissionManagerImpl implements MissionManager {

	private SuperHeroManager superHeroManager;
	private VillainManager villainManager;
    private JdbcTemplate jdbc;

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
	public void createMission(Mission mission)
    {
		SimpleJdbcInsert insertMission=new SimpleJdbcInsert(jdbc).withTableName("missions").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters=new MapSqlParameterSource()
                .addValue("heroid",mission.getHero().getId())
                .addValue("villainid",mission.getVillain().getId())
                .addValue("location",mission.getLocation())
                .addValue("timestamp",mission.getDate())
                .addValue("herowon",mission.getHeroWon());
        Number id=insertMission.executeAndReturnKey(parameters);
        mission.setId(id.longValue());
	}

	public List<Mission> findAllMissions()
    {

	}

	/**
	 * 
	 * @param superHero
	 */
	public List<Mission> findMissionsByHero(SuperHero superHero) {
		// TODO - implement MissionManagerImpl.findMissionsByHero
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param villain
	 */
	public List<Mission> findMissionsByVillain(Villain villain) {
		// TODO - implement MissionManagerImpl.findMissionsByVillain
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Mission getMissionByID(Long id) {
		// TODO - implement MissionManagerImpl.getMissionByID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mission
	 */
	public void updateMission(Mission mission) {
		// TODO - implement MissionManagerImpl.updateMission
		throw new UnsupportedOperationException();
	}

}