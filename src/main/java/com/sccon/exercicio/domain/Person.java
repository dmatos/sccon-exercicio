package com.sccon.exercicio.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String nome;
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;
    @Temporal(TemporalType.DATE)
    private LocalDate dataDeAdmisssao;

    protected Person(){}
}
