package br.com.igortullio.maisvidav2.config;

import br.com.igortullio.maisvidav2.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;

@Configuration
public class DevConfig {

    @Autowired
    private DbService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.populaBanco();
        return true;
    }

}
