package info.coolchatserver.merit;

import dev.personnummer.PersonnummerException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public class Cli {
    Scanner scanner;
    public void Run(){
        System.out.println("What do you wanna do today?");
        System.out.println("1. Create a student");
        System.out.println("2. Login with to a info.coolchatserver.merit.Student");
        System.out.println("3. Delete a student");
        scanner = new Scanner(System.in);

        try{
            switch (scanner.nextInt()){
                case 1 -> {
                    createNewStudent();
                }
                case 2 -> {
                    loginToStudent();
                }
                case 3 -> {
                    //deleteStudent();
                }
                default -> throw new RuntimeException();
            }
        } catch (RuntimeException | PersonnummerException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loginToStudent() throws PersonnummerException, IOException {
        System.out.println("What's the students SSN");
        Student student = new Student(scanner.next(), null);
        firstTimeRun(student);
    }

    private void createNewStudent() throws PersonnummerException, IllegalArgumentException{
        System.out.println("What's the students SSN");
        Student student = new Student(scanner.next(), null);
    }

    private void firstTimeRun(Student student) throws PersonnummerException, IOException {
        for (Course course : ReadGrades.readCourses(student.getSSN())
             ) {
            student.addCourse(course);
        }
        mainStudentCLI(student);
    }
    private void mainStudentCLI(@NotNull Student student){
        while (true) {
            int index = 1;
            if (student.getCourses() != null) {
                for (Course course : student.getCourses()
                ) {
                    System.out.println(index + ". " + course.getName());
                    index++;
                }
            }

            System.out.println("99. Add course");
            System.out.println("0. Save to file");
            try{
                switch (scanner.nextInt()){
                    case 99 -> {
                        createNewCourse(student);
                    }
                    case 0 -> {
                        ReadGrades.saveStudentToFile(student);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void createNewCourse(Student student) throws PersonnummerException, IOException {
        System.out.println("What's the course name");
        String courseName = scanner.next();
        courseName += scanner.nextLine();
        System.out.println("What's  the course points");
        int coursePoint;
        try {
            coursePoint = scanner.nextInt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("What's your grade?");
        String grade = scanner.next();

        student.addCourse(new Course(courseName, coursePoint, grade));

    }
}
