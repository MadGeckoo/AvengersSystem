import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class SuperHeroManagerImpl implements SuperHeroManager {

    private final DataSource dataSource;
    public SuperHeroManagerImpl(DataSource dataSource)
    {
        this.dataSource=dataSource;
    }


	public void addHero(SuperHero superHero) {
        if (superHero == null) {
            throw new IllegalArgumentException("superhero is null");
        }
        if (superHero.getId() != null)
        {
            throw new IllegalArgumentException("superhero id is already set");
        }
        if (superHero.getSuperName().equals(""))
        {
            throw new IllegalArgumentException("hero with no supername");
        }

	}


	public SuperHero getHeroByID(Long id) {

	}

	public void removeHero(SuperHero superHero) {
		// TODO - implement SuperHeroManagerImpl.removeHero
		throw new UnsupportedOperationException();
	}

	public void updateHero(SuperHero superHero) {
		// TODO - implement SuperHeroManagerImpl.updateHero
		throw new UnsupportedOperationException();
	}


    public List<SuperHero> getAllSuperHeroes() throws SuperHeroException {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("select * from superheroes")) {
                try (ResultSet rs = st.executeQuery()) {
                    List<SuperHero> superheroes = new ArrayList<>();
                    while (rs.next()) {
                        superheroes.add(new SuperHero(rs.getLong("id"), rs.getString("supername"), rs.getString("realname"),rs.getString("realsurname")));
                    }
                    return superheroes;
                }
            }
        } catch (SQLException e) {
            throw new SuperHeroException("database select failed", e);
        }
    }
}