public class Villain {

	private long id;
	private String villainName;
	private String realName;
	private String realSurname;

	public Villain() {
		// TODO - implement Villain.Villain
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param villainName
	 * @param realName
	 * @param realSurname
	 */
	public Villain(String villainName, String realName, String realSurname) {
		// TODO - implement Villain.Villain
		throw new UnsupportedOperationException();
	}

	public long getId() {
		return this.id;
	}

	public String getVillainName() {
		return this.villainName;
	}

	/**
	 * 
	 * @param villainName
	 */
	public void setVillainName(String villainName) {
		this.villainName = villainName;
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

}