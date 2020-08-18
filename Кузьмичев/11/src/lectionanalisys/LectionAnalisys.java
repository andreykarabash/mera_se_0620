package lectionanalisys;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LectionAnalisys {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        
        Student newStudent = new Student("Joe");
        
        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));
        
        students.add(newStudent);



        newStudent = new Student("Kate");

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Sam");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Julia");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));
        
        students.add(newStudent);



        newStudent = new Student("Ron");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Will");
        
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Jane");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Barbara");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));
        newStudent.addAttendedLection(new Lection("Physical training", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Glen");

        newStudent.addAttendedLection(new Lection("Math", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));

        students.add(newStudent);



        newStudent = new Student("Sofia");

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        newStudent.addAttendedLection(new Lection("Philosophy", genLocalDate()));
        newStudent.addAttendedLection(new Lection("English", genLocalDate()));
        newStudent.addAttendedLection(new Lection("History", genLocalDate()));

        students.add(newStudent);



        System.out.println("Subtask 1.");
        System.out.println("List of students who attended math at least once");

        students.stream()
                .filter((s) -> s.getAttendedLections().stream()
                        .anyMatch((l) -> l.getName().equals("Math")))
                .forEach((s) -> System.out.println(s.getName()));


        System.out.println();
        System.out.println("Subtask 2.");
        System.out.println("Name - Num of attends");

        students.forEach((s) -> System.out.println(s.getName() + " - " + s.getAttendedLections().size()));



        System.out.println();
        System.out.println("Subtask 3.");
        System.out.println("List of the most attended subjects");

        Map<String, Integer> mostAttendedItems = new HashMap<>();
        students.forEach((s) ->
                s.getAttendedLections().forEach((l) ->
                        mostAttendedItems.put(l.getName(), mostAttendedItems.getOrDefault(l.getName(), 0) + 1)));
        mostAttendedItems.keySet().stream()
                .sorted((i1, i2) -> mostAttendedItems.get(i2) - mostAttendedItems.get(i1))
                .limit(3)
                .forEach(System.out::println);



        System.out.println();
        System.out.println("Subtask 4.");
        System.out.println("List of students with the most attendance");

        students.stream()
                .sorted((s1, s2) -> s2.getAttendedLections().size() - s1.getAttendedLections().size())
                .limit(3)
                .forEach((l) -> System.out.println(l.getName()));



        System.out.println();
        System.out.println("Subtask 5.");
        System.out.println("Item - <number_of_different_students>");

        Map<String, Set<String>> itemsAndNames = new HashMap<>();
        students.forEach((s) ->
                s.getAttendedLections().forEach((l) ->
                itemsAndNames.put(l.getName(), new HashSet<>())));
        students.forEach((s) ->
                s.getAttendedLections().forEach((l) ->
                itemsAndNames.get(l.getName()).add(s.getName())));
        itemsAndNames.keySet()
                .forEach((i) -> System.out.println(i + " - " + itemsAndNames.get(i).size()));
    }

    private static LocalDate genLocalDate() {
        return LocalDate.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth(),
                ThreadLocalRandom.current().nextInt(1,LocalDate.now().lengthOfMonth()));
    }
}
