
import java.util.Date;


public class Mission {

	private Long id;
	private String location;
	private Date date;
	private SuperHero hero;
	private Villain villain;
	private boolean heroWon;

	public Mission() {
		// TODO - implement Mission.Mission
		throw new UnsupportedOperationException();
	}



    /**
	 * 
	 * @param location
	 * @param date
	 * @param hero
	 * @param villain
	 * @param heroWon
	 */
    public Mission(Long id, String location, Date date, SuperHero hero, Villain villain, boolean heroWon) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.hero = hero;
        this.villain = villain;
        this.heroWon = heroWon;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Villain getVillain() {
		return this.villain;
	}

	/**
	 * 
	 * @param villain
	 */
	public void setVillain(Villain villain) {
		this.villain = villain;
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