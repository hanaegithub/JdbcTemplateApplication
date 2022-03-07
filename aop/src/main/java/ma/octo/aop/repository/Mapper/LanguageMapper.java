package ma.octo.aop.repository.Mapper;

import ma.octo.aop.entity.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements RowMapper<Language> {
    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language l=new Language(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("fileExtension"));

        return l;
    }
}
