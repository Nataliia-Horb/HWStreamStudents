package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Student s1 = new Student("001", "Aleks", "Groz");
        s1.addBook("Java 8 extended");
        s1.addBook("Spring boot in action");
        s1.addBook("Effictive Java");

        Student s2 = new Student("002", "Shlomi", "Tailer");
        s2.addBook("Effictive Java");
        s2.addBook("Introduction to HTML");

        Student s3 = new Student("003", "Petr", "Tailer");
        s3.addBook("Effictive Java");
        s3.addBook("Introduction to HTML");

        List<Student> students = new ArrayList<>();
        students.add(s2);
        students.add(s1);
        students.add(s3);

        /**
         * Count number of students with same Second name
         */

        Map<String, Integer> map = students.stream().collect(Collectors.toMap(s -> s.getFamilyName(),
                v -> 1, (v1, v2) -> v1 + v2));
        System.out.println(map);
        map.entrySet().stream().filter(x -> x.getValue() > 1).forEach(System.out::println);

        System.out.println("********************************");


        /**
        * How many users red the same book
        */

        Map<String, Long> res2 = students.stream().map(student ->
                {
                    Map<String, String> m = new HashMap<>();
                    student.getBooks().forEach(b -> m.put(b, student.getId()));
                    return m;
                }
        ).flatMap(c -> c.entrySet().stream()).collect(
                Collectors.groupingBy(x -> x.getKey(), Collectors.counting()));
        System.out.println(res2);
    }
}