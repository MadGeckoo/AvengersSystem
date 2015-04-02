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
	public Mission createMission(Mission mission) {
		// TODO - implement MissionManagerImpl.createMission
		throw new UnsupportedOperationException();
	}

	public List<Mission> findAllMissions() {
		// TODO - implement MissionManagerImpl.findAllMissions
		throw new UnsupportedOperationException();
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
	 * @param superHero
	 * @param villain
	 */
	public List<Mission> findMissionsHeroVsVillain(SuperHero superHero, Villain villain) {
		// TODO - implement MissionManagerImpl.findMissionsHeroVsVillain
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id
	 */
	public Mission getMissionByID(long id) {
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