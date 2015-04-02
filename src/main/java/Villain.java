public class Villain {

	private Long id;
	private String villainName;
	private String realName;
	private String realSurname;

	public Villain() {
		// TODO - implement Villain.Villain
		throw new UnsupportedOperationException();
	}

    public Villain(Long id, String villainName, String realName, String realSurname) {
        this.id = id;
        this.villainName = villainName;
        this.realName = realName;
        this.realSurname = realSurname;
    }

    public void setId(long id) {
        this.id = id;
    }
	public Long getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Villain villain = (Villain) o;

        if (!id.equals(villain.id)) return false;
        if (!realName.equals(villain.realName)) return false;
        if (!realSurname.equals(villain.realSurname)) return false;
        if (!villainName.equals(villain.villainName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + villainName.hashCode();
        result = 31 * result + realName.hashCode();
        result = 31 * result + realSurname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Villain{" +
                "id=" + id +
                ", villainName='" + villainName + '\'' +
                ", realName='" + realName + '\'' +
                ", realSurname='" + realSurname + '\'' +
                '}';
    }
}