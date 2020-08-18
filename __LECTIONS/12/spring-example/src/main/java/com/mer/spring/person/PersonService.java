package com.mer.spring.person;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.mer.spring.dto.Person;

@Service
public class PersonService {


    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PostConstruct
    private void doSomework() {
        Person p1 = new Person("Вася", "Пупкин");
        Person p2 = new Person("Иван", "Сидоров");
        Person p3 = new Person("Анна", "Иванова");
        Person p4 = new Person("Пётр", "Сидоров");

        personRepository.savePerson(p1);
        personRepository.savePerson(p2);
        personRepository.savePerson(p3);
        personRepository.savePerson(p4);
        System.out.println("doSomework вызван, люди сохранены");
    }


    public List<Person> findByLastName(String lastName) {
        return personRepository.findByLastName(lastName);
    }

    @Scheduled(fixedRate = 1000)
    public void printAnyPerson() {

        final List<Person> all = personRepository.findAll();
        final Person person = all.get(new Random().nextInt(all.size()));
        System.out.println("Случайно выбранный человек печатается каждые 1 секунд: " + person.getLastName());
    }

    @Scheduled(fixedRate = 1000)
    public void wait10Second() throws InterruptedException {
        System.out.println("Сплю 10 секунд");
        Thread.sleep(10000);

    }


    public void savePerson(Person person) {
        personRepository.savePerson(person);
    }




    public void sayHelloToPerson(Person person) {
        System.out.println("Привет, " + person.getName());
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
