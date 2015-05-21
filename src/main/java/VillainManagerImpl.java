import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

public class VillainManagerImpl implements VillainManager {

    private JdbcTemplate jdbc;

    final static Logger log = LoggerFactory.getLogger(VillainManagerImpl.class);


    public VillainManagerImpl(DataSource dataSource)
    {
        this.jdbc=new JdbcTemplate(dataSource);
    }

    @Override
	public void addVillain(Villain villain)
    {
        validateVillain(villain);
		SimpleJdbcInsert insertVillain=new SimpleJdbcInsert(jdbc).withTableName("villains").usingGeneratedKeyColumns("id");

        SqlParameterSource parameters=new MapSqlParameterSource().addValue("villainname",villain.getVillainName()).addValue("realname",villain.getRealName()).addValue("realsurname",villain.getRealSurname());

        Number id=insertVillain.executeAndReturnKey(parameters);
        villain.setId(id.longValue());
        log.debug("created {}",villain);


    }

    private RowMapper<Villain> villainMapper=(rs,rowNum)->new Villain(rs.getLong("id"),rs.getString("villainname"),rs.getString("realname"),rs.getString("realsurname"));

    @Override
	public Villain getVillainByID(Long id)
    {
        log.debug("villain with id {} was selected",id);
        return jdbc.queryForObject("SELECT * FROM villains WHERE id=?",villainMapper,id);
	}

    @Override
	public void removeVillain(Villain villain)
    {
        validateVillain(villain);
		jdbc.update("DELETE FROM villains WHERE id=?", villain.getId());
        log.debug("deleted villain {}", villain.getId());

    }

    @Override
	public void updateVillain(Villain villain)
    {
        validateVillain(villain);
        jdbc.update("UPDATE villains SET villainname=?,realname=?,realsurname=? where id=?",villain.getVillainName(),villain.getRealName(),villain.getRealSurname(),villain.getId());
        log.debug("updated villain {}", villain.getId());

    }

    @Transactional
    @Override
    public List<Villain> getAllVillains()
    {
        return jdbc.query("SELECT * FROM villains",villainMapper);
    }

    private void validateVillain(Villain villain)
    {
        if (villain==null)
        {
            log.error(" villain is null ");
            throw new IllegalArgumentException("villain is null");
        }

        if (villain.getVillainName()==null || villain.getVillainName().equals(""))
        {
            log.error(" villain doesn't have villain name ");
            throw new IllegalStateException("villain with no villain name");
        }

    }
}