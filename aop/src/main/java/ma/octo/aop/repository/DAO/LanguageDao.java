package ma.octo.aop.repository.DAO;

import ma.octo.aop.entity.Language;

import java.util.List;

public interface LanguageDao {


    public List<Language> getLanguages();
    public void create(Language language);
    public List<Language> findLanguageByExtension(String fileExtension);

}
