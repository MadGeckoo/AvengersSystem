public class SuperHero {

	private Long id;
	private String superName;
	private String realName;
	private String realSurname;

	public SuperHero() {
	}

	public SuperHero(Long id,String superName, String realName, String realSurname) {
        if (superName.equals(""))
        {
            throw new IllegalArgumentException("Hero has to have super name");
        }
        this.id=id;
		this.superName=superName;
        this.realName=realName;
        this.realSurname=realSurname;
    }

	public String getSuperName() {
		return this.superName;
	}

	public Long getId() {
		return this.id;
	}

	public String getRealName() {
		return this.realName;
	}

    public void setId(Long id) {
        this.id = id;
    }

    public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealSurname() {
		return this.realSurname;
	}

	public void setRealSurname(String realSurname) {
		this.realSurname = realSurname;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}

    @Override
    public String toString() {
        return "SuperHero{" +
                "id=" + id +
                ", superName='" + superName + '\'' +
                ", realName='" + realName + '\'' +
                ", realSurname='" + realSurname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuperHero superHero = (SuperHero) o;

        if (!realName.equals(superHero.realName)) return false;
        if (!realSurname.equals(superHero.realSurname)) return false;
        if (!superName.equals(superHero.superName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = superName.hashCode();
        result = 31 * result + realName.hashCode();
        result = 31 * result + realSurname.hashCode();
        return result;
    }
}