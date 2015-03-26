import java.util.List;

public interface SuperHeroManager {

	/**
	 * 
	 * @param superHero
	 */
	void addHero(SuperHero superHero) throws SuperHeroException;

	/**
	 * 
	 * @param superHero
	 */
	void removeHero(SuperHero superHero) throws SuperHeroException;

	/**
	 * 
	 * @param id
	 */
	SuperHero getHeroByID(Long id) throws SuperHeroException;

	/**
	 * 
	 * @param superHero
	 */
	void updateHero(SuperHero superHero) throws SuperHeroException;

    List<SuperHero> getAllSuperHeroes() throws SuperHeroException;

}