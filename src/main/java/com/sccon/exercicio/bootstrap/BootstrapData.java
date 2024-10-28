package com.sccon.exercicio.bootstrap;

import com.sccon.exercicio.domain.Person;
import com.sccon.exercicio.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class BootstrapData implements CommandLineRunner {

    private final PersonRepository personRepository;

    public BootstrapData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person jose = new Person(1L, "José da Silva", LocalDate.of(2000, 4, 6), LocalDate.of(2020, 5, 10));
        Person joao = new Person(2L, "João dos Santos", LocalDate.of(2001, 5, 1), LocalDate.of(2021, 6, 1));
        Person jeremias = new Person(3L, "Jeremias de Jesus",LocalDate.of(2001, 7, 1), LocalDate.of(2022, 5, 1));
        personRepository.saveAll(Arrays.asList(jose, joao, jeremias));
    }
}
