package com.sccon.exercicio.service;

import com.sccon.exercicio.dto.PersonDTO;
import com.sccon.exercicio.exceptions.OutputFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class SalaryService {

    @Value("${exercicio.min.wage:1302.00}")
    private String minWage;

    private final AgeService ageService;
    private final BigDecimal initialSalary = new BigDecimal("1558.00");

    public SalaryService(AgeService ageService) {
        this.ageService = ageService;
    }

    public BigDecimal getSalary(String output, PersonDTO personDTO) throws OutputFormatException {
        if(!StringUtils.hasText(output)){
            throw new OutputFormatException();
        }
        switch (output){
            case "full":
                return calcPersonSalary(personDTO);
            case "min":
                return calcPersonSalary(personDTO)
                        .divide(new BigDecimal(minWage), RoundingMode.CEILING)
                        .setScale(2, RoundingMode.CEILING);
            default:
                throw new OutputFormatException();
        }
    }

    public BigDecimal calcPersonSalary(PersonDTO personDTO){
        LocalDate dataDeAdmisssao = personDTO.getDataDeAdmisssao();
        Long elapsedTimeInYears = ageService.getElapsedTimeInYears(dataDeAdmisssao);
        BigDecimal salary = initialSalary;
        while(elapsedTimeInYears > 0L){
            salary = salary.multiply(new BigDecimal("1.18"))
                    .add(new BigDecimal(500));
            elapsedTimeInYears--;
        }
        return salary.setScale(2, RoundingMode.CEILING);
    }
}
