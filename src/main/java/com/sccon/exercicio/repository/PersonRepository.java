package com.sccon.exercicio.repository;

import com.sccon.exercicio.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> {
    List<Person> findAllByOrderByNomeAsc();
    Person save(Person person);
    void deleteById(Long id);
    Optional<Person> findById(Long id);
}
