package com.sccon.exercicio.dto.converter;

import com.sccon.exercicio.domain.Person;
import com.sccon.exercicio.dto.PersonDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class PersonDTOToPerson implements Converter<PersonDTO, Person> {
    @Override
    public Person convert(PersonDTO source) {
        if(ObjectUtils.isEmpty(source)){
            return null;
        }
        final Person person = new Person(
            source.getId(),
                source.getNome(),
                source.getDataNascimento(),
                source.getDataDeAdmisssao()
        );
        return person;
    }
}
