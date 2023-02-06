import dev.personnummer.PersonnummerException;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ReadGradesTest {
    private final String realTestSSN = "690813-3272";
    private final Path basePath = Paths.get("students/");

    @Test
    void saveStudentToFile(){
        try {
            Student student = new Student(realTestSSN, null);
            student.addCourse(new Course("Svenska 1", 100, "A"));
            student.addCourse(new Course("Svenska 2", 100, "C"));

            ReadGrades.saveStudentToFile(student);

            File file = new File(basePath.toString(), realTestSSN);

            assertTrue(file.exists());
            assertEquals("Course{name='SVENSKA 1', grade='A', coursePoints=100, gradeMerit=20.0}",ReadGrades.readCourses(realTestSSN).get(0).toString());
            assertEquals("Course{name='SVENSKA 2', grade='C', coursePoints=100, gradeMerit=15.0}",ReadGrades.readCourses(realTestSSN).get(1).toString());

        } catch (PersonnummerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
