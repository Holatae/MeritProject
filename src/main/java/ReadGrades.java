import dev.personnummer.Personnummer;
import dev.personnummer.PersonnummerException;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadGrades {

    public static ArrayList<Course> readCourses(String SSN) {
        ArrayList<Course> coursesList;
        try {
            coursesList = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File((new Personnummer(SSN)).format())));

            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("#")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                String[] lineArr = line.split(", ");
                coursesList.add(new Course(lineArr[0], Integer.parseInt(lineArr[1]), lineArr[2]));
                line = bufferedReader.readLine();
            }
        } catch (IOException | PersonnummerException e) {
            throw new RuntimeException(e);
        }
        return coursesList;
    }

    public static void appendCourse(Course course, String SSN) throws PersonnummerException, IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(new File(new Personnummer(SSN).format())))) {
                bufferedWriter.append(course.getGrade().toUpperCase()
                        + ", " +
                        course.getCoursePoints() +
                        ", " +
                        course.getGrade());
        }



    }

    public static void saveStudentToFile(Student student) {
        Logger logger = Logger.getLogger(ReadGrades.class.getName());
        ArrayList<String> commentCourses = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            // Gets all the comment courses into an Arraylist. This is so this is so
            // they will be in the new saved file

            bufferedReader = new BufferedReader(new FileReader(new File(student.getSSN())));
            String line = bufferedReader.readLine();
            while (line != null){
                if (line.contains("#")){
                    commentCourses.add(line);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            logger.log(Level.FINE, "Read grades for SSN: " + student.getSSN() + " Doesn't exist");
        }
        // Write the courses to file
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(student.getSSN())));
            for (Course course : student.getCourses()
                 ) {
                bufferedWriter.write(course.getName() +
                        ", "
                        + course.getCoursePoints() +
                        ", "
                        + course.getGrade()
                        + '\n');
            }

            for (String line: commentCourses
                 ) {
                bufferedWriter.write(line);
            }

            bufferedWriter.close();

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not save Courses to file");
            throw new RuntimeException(e);
        }


    }

}
