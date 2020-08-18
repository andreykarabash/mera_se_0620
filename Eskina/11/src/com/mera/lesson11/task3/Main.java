package com.mera.lesson11.task3;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Lection> mySchedule = new HashSet<>();
        //add lections in schedule for 2 weeks
        //1st week - Monday,2020.04.06
        LocalDate date = LocalDate.of(2020, 4, 6);
        mySchedule = fillWeekSchedule(date);
        mySchedule.addAll(fillWeekSchedule(date.plusDays(7)));

        //create list of the students visited
        List<Student> students = new ArrayList<>();
        Student Tom = new Student("Том Сойер");
        Student Fedor = new Student("Дядя федор");
        Student Denis = new Student("Денис Кораблёв");
        Student Electronic = new Student("Электроник");
        Student Vasechkin = new Student("Васечкин");
        Student Peppy = new Student("Пеппи Длинный Чулок");
        Student Alice = new Student("Алиса Селезнева");
        Student Vasya = new Student("Вася Куролесов");
        Student Vitya = new Student("Витя Малеев");
        Student Lusya= new Student("Люся Синицына");

        students = Arrays.asList(Tom, Fedor, Denis, Electronic, Vasechkin, Peppy, Alice, Vasya, Vitya, Lusya);

        System.out.println("Заполняю персональные расписания студентов");
        Set<Lection> finalMySchedule = mySchedule;
        students.forEach(student -> {
            student.fillPersonalSchedule(finalMySchedule);
            System.out.println(student);
        });

        VisitStatistics statistics = new VisitStatistics(mySchedule, students);

        System.out.println("Студенты, посетившие хоть раз матанализ:");
        List<Student> tempList = statistics.atLeastOnceVisited(Lection.MATH);
        tempList.forEach(student->System.out.println(student.getName()));

        System.out.println("Статистика посещений в формате \"Имя - количество посещенных лекций\"");
        Map<String, Integer> tempMap = statistics.createMapWithNumberOfVisitedLections();
        tempMap.entrySet().forEach(System.out::println);

        System.out.println("Самые посещаемые курсы:");
        List<String> popularCourses = statistics.findTheMostPopularCourses();
        popularCourses.forEach(System.out::println);

        System.out.println("Самые ответственные студенты, которые посетили наибольшее количество лекций в день:");
        List<Map.Entry<String, Map.Entry<LocalDate, Long>>> mostActive = statistics.getMostActiveStudents();
        mostActive.forEach(System.out::println);

        System.out.println("Список посетивших студентов по каждой дисциплине");
        Map<String, Set<String>> visitMap = statistics.createGroupListForEveryCourse();
        visitMap.entrySet().forEach(System.out::println);
    }

    public static Set<Lection> fillWeekSchedule(LocalDate dateStart) {
        //Monday
        LocalDate date = dateStart;
        Set<Lection> schedule = new HashSet<>();
        schedule.add(new Lection(Lection.MATH, date));
        schedule.add(new Lection(Lection.PHILOSOPHY, date));
        schedule.add(new Lection(Lection.HISTORY, date));
        schedule.add(new Lection(Lection.SPORT, date));
        date = date.plusDays(1);
        //Tuesday
        schedule.add(new Lection(Lection.ENGLISH, date));
        date = date.plusDays(1);
        //Wednesday
        schedule.add(new Lection(Lection.HISTORY, date));
        schedule.add(new Lection(Lection.SPORT, date));
        date = date.plusDays(1);
        //Thursday
        schedule.add(new Lection(Lection.PHILOSOPHY, date));
        date = date.plusDays(1);
        //Friday
        schedule.add(new Lection(Lection.MATH, date));
        schedule.add(new Lection(Lection.ENGLISH, date));
        schedule.add(new Lection(Lection.SPORT, date));
        return schedule;
    }
}

