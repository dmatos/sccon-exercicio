package com.sccon.exercicio.service;

import com.sccon.exercicio.domain.Person;
import com.sccon.exercicio.exceptions.OutputFormatException;
import com.sccon.exercicio.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = {
        "exercicio.date.current.day=7",
        "exercicio.date.current.month=2",
        "exercicio.date.current.year=2023"
})
class PersonServiceTest {

    @Autowired
    PersonService personService;
    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setup(){
        Person jose = new Person(1L, "José da Silva", LocalDate.of(2000, 4, 6), LocalDate.of(2020, 5, 10));
        personRepository.save(jose);
    }

    @ParameterizedTest
    @CsvSource({"days, 8342","months, 274","years, 22"})
    void getAge(String output, Long elapsedTime) throws OutputFormatException {
        Optional<Long> daysOpt = personService.getAge(1L, output);
        assertNotNull(daysOpt);
        assertTrue(daysOpt.isPresent());
        assertEquals(elapsedTime, daysOpt.get());
    }

    @Test
    void getAgeThrowsOutputFormatExceptionByNull(){
        assertThrows(OutputFormatException.class, () -> personService.getAge(1L, null));
    }

    @Test
    void getAgeThrowsOutputFormatExceptionByWrongText(){
        assertThrows(OutputFormatException.class, () -> personService.getAge(1L, ""));
    }
}
