package ma.octo.aop.repository.impl;

import ma.octo.aop.entity.Language;
import ma.octo.aop.repository.DAO.LanguageDao;
import ma.octo.aop.repository.Mapper.LanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@EnableAspectJAutoProxy(proxyTargetClass=true)
@Transactional
@Service
public class LanguageJDBCTemplate implements LanguageDao {
    //private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    @Transactional
    public List<Language> getLanguages() {
        String SQL = "select * from Language";
        List <Language> languages = jdbcTemplateObject.query(SQL, new LanguageMapper());
        return languages;
    }

    @Override
    @Transactional
    public void create(Language language) {
        SimpleJdbcInsert simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplateObject);
        simpleJdbcInsert.withTableName("LANGUAGE");
        Map<String,Object> parameters=new HashMap<String, Object>(4);
        parameters.put("id",language.getId());
        parameters.put("name",language.getName());
        parameters.put("author",language.getAuthor());
        parameters.put("fileExtension",language.getFileExtension());
        simpleJdbcInsert.execute(parameters);
        return;

       /* String SQL = "insert into Language (id, name, author, fileExtension) values (?, ?, ?, ?)";

        jdbcTemplateObject.update( SQL, id, name, author, fileExtension);
        System.out.println("Created Record Name = " + id + " name = " + name);
        return;*/
    }

    @Override
    public List<Language> findLanguageByExtension(String fileExtension) {
        String SQL = "select * from Language where fileExtension = '"+fileExtension+"'";
        List <Language> languages = jdbcTemplateObject.query(SQL, new LanguageMapper());
        return languages;
    }
}
