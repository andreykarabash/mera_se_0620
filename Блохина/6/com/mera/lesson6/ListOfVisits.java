package com.mera.lesson6;

import java.util.*;

public class ListOfVisits {
    private Set<Triple<Person, Date, String>> visitSet = new HashSet<>();
    private List<Triple<Person, Date, String>> visitList = new ArrayList<>();

    public Set<Triple<Person, Date, String>> getVisitSet() {
        return visitSet;
    }

    public List<Triple<Person, Date, String>> getVisitList() {
        return visitList;
    }

    public void printVisitSet() {
        for (Triple<Person, Date, String> triple : visitSet) {
            System.out.println(triple.getFirstElement() + ", " + triple.getSecondElement() + ", " + triple.getThirdElement());
        }
    }

    public void getRandomListOfVisits(List<Person> peoples, List<String> locations) {
        final Random random = new Random();

        final int MAX_NUMBER_OF_VISITS = 300;
        Months[] MonthsOfYear = Months.values();

        for ( Person person : peoples ) {
            for (int i = 0; i < MAX_NUMBER_OF_VISITS; i++) {
                String randomLocation = locations.get(random.nextInt(locations.size()));
                Months randomMonth = MonthsOfYear[random.nextInt(MonthsOfYear.length)];
                Date randomDate = new Date(random.nextInt(randomMonth.getMaxDay())+1, randomMonth, 1980);
                Triple<Person, Date, String> visitRecord = new Triple<>(person,randomDate, randomLocation);
                visitSet.add(visitRecord);
                visitList.add(visitRecord);
            }
        }
    }

    public boolean isListAndSetSameLength(){
        return visitSet.size() == visitList.size();
    }
}
