package com.sccon.exercicio.dto.converter;

import com.sccon.exercicio.domain.Person;
import com.sccon.exercicio.dto.PersonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class PersonToPersonDTO implements Converter<Person, PersonDTO> {
    @Override
    public PersonDTO convert(Person source) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }
        return PersonDTO.builder()
                .id(source.getId())
                .nome(source.getNome())
                .dataNascimento(source.getDataNascimento())
                .dataDeAdmisssao(source.getDataDeAdmisssao())
                .build();
    }
}
