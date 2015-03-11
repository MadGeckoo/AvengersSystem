
import java.sql.Timestamp;


public class Mission {

	private long id;
	private String location;
	private Timestamp timestamp;
	private SuperHero hero;
	private Villain villan;
	private boolean heroWon;

	public Mission() {
		// TODO - implement Mission.Mission
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param location
	 * @param timestamp
	 * @param hero
	 * @param villain
	 * @param heroWon
	 */
	public Mission(String location, Timestamp timestamp, SuperHero hero, Villain villain, boolean heroWon) {
		// TODO - implement Mission.Mission
		throw new UnsupportedOperationException();
	}

	public long getId() {
		return this.id;
	}

	public String getLocation() {
		return this.location;
	}

	/**
	 * 
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Villain getVillan() {
		return this.villan;
	}

	/**
	 * 
	 * @param villan
	 */
	public void setVillan(Villain villan) {
		this.villan = villan;
	}

	public SuperHero getHero() {
		return this.hero;
	}

	/**
	 * 
	 * @param hero
	 */
	public void setHero(SuperHero hero) {
		this.hero = hero;
	}

	public boolean getHeroWon() {
		return this.heroWon;
	}

	/**
	 * 
	 * @param heroWon
	 */
	public void setHeroWon(boolean heroWon) {
		this.heroWon = heroWon;
	}

}