import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;

public class SuperHeroManagerImpl implements SuperHeroManager {


    private JdbcTemplate jdbc;

    public SuperHeroManagerImpl(DataSource dataSource)
    {
        this.jdbc=new JdbcTemplate(dataSource);
    }

    @Override
    public void addHero(SuperHero hero)
    {
        SimpleJdbcInsert insertHero=new SimpleJdbcInsert(jdbc).withTableName("superheroes").usingGeneratedKeyColumns("id");

        SqlParameterSource parameters=new MapSqlParameterSource().addValue("supername", hero.getSuperName()).addValue("realname",hero.getRealName()).addValue("realsurname", hero.getRealSurname());

        Number id=insertHero.executeAndReturnKey(parameters);
        hero.setId(id.longValue());

    }

    private RowMapper<SuperHero> heroMapper=(rs,rowNum)->new SuperHero(rs.getLong("id"),rs.getString("supername"),rs.getString("realname"),rs.getString("realsurname"));

    @Override
    public SuperHero getHeroByID(Long id)
    {
        return jdbc.queryForObject("SELECT * FROM superheroes WHERE id=?",heroMapper,id);
    }

    @Override
    public void removeHero(SuperHero hero)
    {
        jdbc.update("DELETE FROM superheroes WHERE id=?", hero.getId());
    }

    @Override
    public void updateHero(SuperHero hero)
    {
        jdbc.update("UPDATE superheroes SET supername=?,realname=?,realsurname=? where id=?",hero.getSuperName(),hero.getRealName(),hero.getRealSurname(),hero.getId());
    }

    @Transactional
    @Override
    public List<SuperHero> getAllSuperHeroes()
    {
        return jdbc.query("SELECT * FROM superheroes",heroMapper);
    }


    /*private final DataSource dataSource;
    public SuperHeroManagerImpl(DataSource dataSource)
    {
        this.dataSource=dataSource;
    }


	public void addHero(SuperHero superHero) throws SuperHeroException{
        if (superHero == null) {
            throw new IllegalArgumentException("superhero is null");
        }
        if (superHero.getSuperName().equals(""))
        {
            throw new IllegalArgumentException("hero with no supername");
        }

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("insert into superheroes (supername,realname,realsurname) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
                st.setString(1, superHero.getSuperName());
                st.setString(2, superHero.getRealName());
                st.setString(3, superHero.getRealSurname());
                st.executeUpdate();
                try (ResultSet keys = st.getGeneratedKeys()) {
                    if (keys.next()) {
                        Long id = keys.getLong(1);
                        superHero.setId(id);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SuperHeroException("database insert failed", e);
        }
	}


    public SuperHero getHeroByID(Long id) throws SuperHeroException {
            try (Connection con = dataSource.getConnection()) {
                try (PreparedStatement st = con.prepareStatement("select * from superheroes where id = ?")) {
                    st.setLong(1, id);
                    try (ResultSet rs = st.executeQuery()) {
                        if (rs.next()) {
                            return new SuperHero(rs.getLong("id"), rs.getString("supername"), rs.getString("realname"),rs.getString("realsurname"));
                        } else {
                            return null;
                        }
                    }
                }
            } catch (SQLException e) {
                throw new SuperHeroException("database select failed", e);
            }
    }

    public void updateHero(SuperHero superHero) throws SuperHeroException{
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("update superheroes set supername=?, realname=?, realsurname=? where id=?")) {
                st.setString(1, superHero.getSuperName());
                st.setString(2, superHero.getRealName());
                st.setString(3, superHero.getRealSurname());
                st.setLong(4, superHero.getId());
                int n = st.executeUpdate();
                if (n != 1) {
                    throw new SuperHeroException("not updated superhero with id " + superHero.getId(), null);
                }
            }
        } catch (SQLException e) {
            throw new SuperHeroException("database update failed", e);
        }
    }



    public void removeHero(SuperHero superHero)throws SuperHeroException {
        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement st = con.prepareStatement("DELETE  FROM superheroes WHERE supername=? and realname=? and realsurname=? and id=?")) {
                st.setString(1, superHero.getSuperName());
                st.setString(2, superHero.getRealName());
                st.setString(3, superHero.getRealSurname());
                st.setLong(4, superHero.getId());
                int n = st.executeUpdate();
                if (n != 1) {
                    throw new SuperHeroException("superhero not deleted with id: " + superHero.getId(), null);
                }
            }
        } catch (SQLException e) {
            throw new SuperHeroException("database update failed", e);
        }
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
    }*/
}