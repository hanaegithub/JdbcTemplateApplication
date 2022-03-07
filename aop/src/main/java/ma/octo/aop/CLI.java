package ma.octo.aop;

import ma.octo.aop.entity.Language;
import ma.octo.aop.repository.DAO.LanguageDao;
import ma.octo.aop.repository.impl.LanguageJDBCTemplate;
import ma.octo.aop.service.LanguageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;
@Configuration
public class CLI {
  private final LanguageService languageService;
  @Bean
  @Primary
  public LanguageDao languageDao() {
    return new LanguageJDBCTemplate();
  }

  public CLI(final LanguageService languageService) {
    this.languageService = languageService;
  }

  public static void main(String[] args) {
   //final var context = new AnnotationConfigApplicationContext(LanguageJDBCTemplate.class);
    //final var cli = new CLI(context.getBean(LanguageService.class));
    //cli.start();
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

    LanguageJDBCTemplate languageJDBCTemplate = (LanguageJDBCTemplate) context.getBean("languageDao", LanguageJDBCTemplate.class);

    Language  haskell = new Language("haskel", "Haskell", "Simon Peyton", "hs");
    Language lua = new Language("lua", "Lua", "Luiz Henrique", "hs");
    Language python = new Language("python", "Python", "Guido van Rossum", "hs");

    System.out.println("------Records Creation--------" );

    languageJDBCTemplate.create(haskell);
    languageJDBCTemplate.create(lua);
    languageJDBCTemplate.create(python);
    System.out.println("------Listing Multiple Records--------" );

    List<Language> languages = languageJDBCTemplate.getLanguages();

    for (Language record : languages) {
      System.out.print("ID : " + record.getId() );
      System.out.print(", Name : " + record.getName() );
      System.out.println(", author : " + record.getAuthor());
    }

    System.out.println("------Listing Multiple Records With Extention--------" );
    List<Language> languagesExtention = languageJDBCTemplate.findLanguageByExtension("hs");

    for (Language record : languagesExtention) {
      System.out.print("ID : " + record.getId() );
      System.out.print(", Name : " + record.getName() );
      System.out.print(", author : " + record.getAuthor());
      System.out.println(", fileExtension : " + record.getFileExtension());
    }
  }

  private void start() {
    final var sc = new Scanner(System.in);
    String cmd;

    while (true) {
      prompt();
      cmd = sc.nextLine();
      try {
        evaluate(cmd);
      } catch (IllegalStateException e) {
        System.out.println("Invalid command, try again");
      } catch (RuntimeException e) {
        break;
      }
    }
  }

  private void evaluate(final String cmd) {
    final var operation = cmd.split(" ")[0];
    switch (operation) {
      case "f": {
        findLanguageById(cmd.split(" ")[1]);
        break;
      }
      case "e": {
        findLanguageByExtension(cmd.split(" ")[1]);
        break;
      }
      case "q": throw new RuntimeException();
      default: throw new IllegalStateException();
    }
  }

  private void findLanguageByExtension(final String extension) {
    final var language = languageService.getLanguageByExtension(extension)
        .map(Language::toString)
        .orElse("Not Found");
    System.out.println(language);
  }

  private void findLanguageById(final String id) {
    final var language = languageService.getLanguageById(id)
        .map(Language::toString)
        .orElse("Not Found");
    System.out.println(language);
  }

  private void prompt() {
    System.out.println("Find language by id: f {id}");
    System.out.println("Find language by extension: e {extension}");
    System.out.println("Quit: q");
  }

}
