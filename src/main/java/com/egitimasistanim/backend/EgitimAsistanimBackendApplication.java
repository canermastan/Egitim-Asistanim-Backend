package com.egitimasistanim.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
    Image upload ayarla
 */
@SpringBootApplication
@EnableJpaAuditing
public class EgitimAsistanimBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgitimAsistanimBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
