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

public class VillainManagerImpl implements VillainManager {

    private JdbcTemplate jdbc;

    public VillainManagerImpl(DataSource dataSource)
    {
        this.jdbc=new JdbcTemplate(dataSource);
    }

    @Override
	public void addVillain(Villain villain)
    {
		SimpleJdbcInsert insertVillain=new SimpleJdbcInsert(jdbc).withTableName("villains").usingGeneratedKeyColumns("id");

        SqlParameterSource parameters=new MapSqlParameterSource().addValue("villainname",villain.getVillainName()).addValue("realname",villain.getRealName()).addValue("realsurname",villain.getRealSurname());

        Number id=insertVillain.executeAndReturnKey(parameters);
        villain.setId(id.longValue());

	}

    private RowMapper<Villain> villainMapper=(rs,rowNum)->new Villain(rs.getLong("id"),rs.getString("villainname"),rs.getString("realname"),rs.getString("realsurname"));

    @Override
	public Villain getVillainByID(Long id)
    {
        return jdbc.queryForObject("SELECT * FROM villains WHERE id=?",villainMapper,id);
	}

    @Override
	public void removeVillain(Villain villain)
    {
		jdbc.update("DELETE FROM villains WHERE id=?", villain.getId());
	}

    @Override
	public void updateVillain(Villain villain)
    {
        jdbc.update("UPDATE villains SET villainname=?,realname=?,realsurname=? where id=?",villain.getVillainName(),villain.getRealName(),villain.getRealSurname(),villain.getId());
	}

    @Transactional
    @Override
    public List<Villain> getAllVillains()
    {
        return jdbc.query("SELECT * FROM villains",villainMapper);
    }
}