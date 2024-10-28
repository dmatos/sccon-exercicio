package com.sccon.exercicio.service;

import com.sccon.exercicio.dto.PersonDTO;
import com.sccon.exercicio.exceptions.OutputFormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = {
        "exercicio.min.wage=1302.00",
        "exercicio.date.current.day=7",
        "exercicio.date.current.month=2",
        "exercicio.date.current.year=2023"
})
class SalaryServiceTest {
    @Autowired
    private SalaryService salaryService;

    @ParameterizedTest
    @CsvSource({"full, 3259.36", "min, 2.51"})
    void calcPersonSalary(String output, Double expectedResult) throws OutputFormatException {
        PersonDTO jose = new PersonDTO(1L, "José da Silva", LocalDate.of(2000, 4, 6), LocalDate.of(2020, 5, 10));
        BigDecimal salary = salaryService.getSalary(output, jose);
        assertEquals(0, salary.compareTo(BigDecimal.valueOf(expectedResult)));
    }

    @Test
    void getAgeThrowsOutputFormatExceptionByNull(){
        assertThrows(OutputFormatException.class, () -> salaryService.getSalary(null, null));
    }

    @Test
    void getAgeThrowsOutputFormatExceptionByWrongText(){
        assertThrows(OutputFormatException.class, () -> salaryService.getSalary("", null));
    }
}
