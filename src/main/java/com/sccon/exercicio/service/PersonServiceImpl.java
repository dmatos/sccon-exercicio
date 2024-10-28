package com.sccon.exercicio.service;

import com.sccon.exercicio.domain.Person;
import com.sccon.exercicio.dto.PersonDTO;
import com.sccon.exercicio.dto.converter.PersonDTOToPerson;
import com.sccon.exercicio.dto.converter.PersonToPersonDTO;
import com.sccon.exercicio.exceptions.OutputFormatException;
import com.sccon.exercicio.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{

    private final PersonToPersonDTO personToPersonDTO;
    private final PersonDTOToPerson personDTOToPerson;
    private final PersonRepository personRepository;
    private final AgeService ageService;

    public PersonServiceImpl(PersonToPersonDTO personToPersonDTO, PersonDTOToPerson personDTOToPerson, PersonRepository personRepository, AgeService ageService) {
        this.personToPersonDTO = personToPersonDTO;
        this.personDTOToPerson = personDTOToPerson;
        this.personRepository = personRepository;
        this.ageService = ageService;
    }

    @Override
    public List<PersonDTO> getAll() {
        List<Person> personList =  personRepository.findAllByOrderByNomeAsc();
        return personList.stream().map(personToPersonDTO::convert).toList();
    }

    @Override
    public Optional<PersonDTO> save(PersonDTO personDTO) {
        Optional<PersonDTO> optionalPersonDTO = findById(personDTO.getId());
        if(optionalPersonDTO.isPresent()){
            return Optional.empty();
        }
        Person person = personDTOToPerson.convert(personDTO);
        if(ObjectUtils.isEmpty(person)){
            return Optional.empty();
        }
        person = personRepository.save(person);
        return convert(Optional.of(person));
    }

    @Override
    public Optional<PersonDTO> delete(Long id) {
        Optional<Person>  personOpt = personRepository.findById(id);
        if(personOpt.isEmpty()){
            return Optional.empty();
        }
        personRepository.deleteById(id);
        return convert(personOpt);
    }

    @Override
    public Optional<PersonDTO> update(PersonDTO personDTO) {
        Optional<PersonDTO> personDTOOptional = this.findById(personDTO.getId());
        if(personDTOOptional.isEmpty()){
            return Optional.empty();
        }
        Person person = personDTOToPerson.convert(personDTO);
        if(ObjectUtils.isEmpty(person)){
            return Optional.empty();
        }
        Person save = personRepository.save(person);
        return convert(Optional.of(save));

    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return convert(optionalPerson);
    }

    @Override
    public Optional<Long> getAge(Long id, String output) throws OutputFormatException {
        Optional<PersonDTO> personDTOOptional = findById(id);
        if(!StringUtils.hasText(output)){
            throw new OutputFormatException();
        }
        if(personDTOOptional.isEmpty()){
            return Optional.empty();
        }
        LocalDate birth = personDTOOptional.get().getDataNascimento();
        switch (output) {
            case "days":
                return Optional.of(ageService.getElapsedTimeInDays(birth));
            case "months":
                return Optional.of(ageService.getElapsedTimeInMonths(birth));
            case "years":
                return Optional.of(ageService.getElapsedTimeInYears(birth));
            default:
                throw new OutputFormatException();
        }
    }

    private Optional<PersonDTO> convert(Optional<Person> optionalPerson){
        if (optionalPerson.isEmpty()) {
            return Optional.empty();
        }
        PersonDTO result = personToPersonDTO.convert(optionalPerson.get());
        if (ObjectUtils.isEmpty(result)) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
