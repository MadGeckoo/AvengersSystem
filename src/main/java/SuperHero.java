public class SuperHero {

	private long id;
	private String superName;
	private String realName;
	private String realSurname;

	public SuperHero() {
		// TODO - implement SuperHero.SuperHero
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param superName
	 * @param realName
	 * @param realSurname
	 */
	public SuperHero(String superName, String realName, String realSurname) {
		// TODO - implement SperHero.SuperHero
		throw new UnsupportedOperationException();
	}

	public String getSuperName() {
		return this.superName;
	}

	public long getId() {
		return this.id;
	}

	public String getRealName() {
		return this.realName;
	}

	/**
	 * 
	 * @param realName
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealSurname() {
		return this.realSurname;
	}

	/**
	 * 
	 * @param realSurname
	 */
	public void setRealSurname(String realSurname) {
		this.realSurname = realSurname;
	}

	/**
	 * 
	 * @param superName
	 */
	public void setSuperName(String superName) {
		this.superName = superName;
	}

}