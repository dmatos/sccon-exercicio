package com.sccon.exercicio.service;

import com.sccon.exercicio.dto.PersonDTO;
import com.sccon.exercicio.exceptions.OutputFormatException;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDTO> getAll();
    Optional<PersonDTO> save(PersonDTO person);
    Optional<PersonDTO> delete(Long id);
    Optional<PersonDTO> update(PersonDTO person);
    Optional<PersonDTO> findById(Long id);
    Optional<Long> getAge(Long id, String output) throws OutputFormatException;
}
