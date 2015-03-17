import java.util.List;

public class MissionManagerImpl implements MissionManager {

	private SuperHeroManager superHeroManager;
	private VillainManager villainManager;

    public MissionManagerImpl()
    {
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