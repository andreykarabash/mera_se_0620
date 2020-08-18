package src.lesson6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class VisitsList {
    public static void main(String[] args){
        List<Triple<Person,Date,String>> visitsArrayList = new ArrayList<>();
        HashSet<Triple<Person,Date,String>> visitsHashSet = new HashSet<>();


        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Jack", "Nicholson"));
        persons.add(new Person("Charles", "Perry"));
        persons.add(new Person("Mark", "Burke"));
        persons.add(new Person("Gilbert", "Maxwell"));
        persons.add(new Person("David", "Hudson"));

        for (Person i: persons){
            for (int x = 0; x < Parameters.params.NUMBER_VISITS_YEAR.getValue(); x++){
                Triple <Person, Date, String> currentVisit = new Triple(i, generateDate(), generateLocation());
                visitsArrayList.add(currentVisit);
                visitsHashSet.add(currentVisit);

            }
        }

        System.out.println("List " + visitsArrayList.size());
        System.out.println("Set " + visitsHashSet.size());

        if (visitsArrayList.size() == visitsHashSet.size()){
            System.out.println("Nobody visited one location twice a day");
        }

    }

    private static Date generateDate(){
        int currentYear = Parameters.params.CURRENT_YEAR.getValue();

        int random = new Random().nextInt(monthParameters.months.values().length);
        String currentMonth = monthParameters.months.values()[random].name();
        int currentDay = new Random().nextInt (monthParameters.months.values()[random].getValue()+1);

        return new Date(currentYear,currentMonth,currentDay);

    }

    private static String generateLocation(){

        int random = new Random().nextInt(Locations.locations.values().length);


        return Locations.locations.values()[random].name();

    }
}


