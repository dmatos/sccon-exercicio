package com.sccon.exercicio.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {
    private Long id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;
    @Temporal(TemporalType.DATE)
    private LocalDate dataDeAdmisssao;
}
