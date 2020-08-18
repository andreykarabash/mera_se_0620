package com.mer.spring.person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.mer.spring.dto.Person;

@Repository
public class PersonRepository {

    private List<Person> persons = new ArrayList<>();

    public void savePerson(Person person) {
        persons.add(person);
    }

    @Cacheable("persons")
    public List<Person> findByLastName(String lastName) {
        System.out.println("Пытаемся получить из хранилища:" + lastName);
        return persons.stream()
                .filter(p -> p.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }


    public List<Person> findAll() {
        return new ArrayList<>(persons);
    }
}
