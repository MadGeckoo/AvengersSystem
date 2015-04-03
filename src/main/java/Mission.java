
import java.time.LocalDate;
import java.util.Date;


public class Mission {

	private Long id;
	private String location;
	private LocalDate date;
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
    public Mission(Long id, String location, LocalDate date, SuperHero hero, Villain villain, boolean heroWon) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mission mission = (Mission) o;

        if (heroWon != mission.heroWon) return false;
        if (date != null ? !date.equals(mission.date) : mission.date != null) return false;
        if (hero != null ? !hero.equals(mission.hero) : mission.hero != null) return false;
        if (id != null ? !id.equals(mission.id) : mission.id != null) return false;
        if (!location.equals(mission.location)) return false;
        if (villain != null ? !villain.equals(mission.villain) : mission.villain != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + location.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (hero != null ? hero.hashCode() : 0);
        result = 31 * result + (villain != null ? villain.hashCode() : 0);
        result = 31 * result + (heroWon ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", hero=" + hero +
                ", villain=" + villain +
                ", heroWon=" + heroWon +
                '}';
    }
}