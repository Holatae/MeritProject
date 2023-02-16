package info.coolchatserver.merit;

import dev.personnummer.PersonnummerException;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseManagerTest {
    private final String realTestSSN = "690813-3272";
    private final Path basePath = Paths.get("students/");

    @Test
    void saveStudentToFile(){
        try {
            Student student = new Student(realTestSSN);
            student.addCourse(new Course("Svenska 1", 100, "A"));
            student.addCourse(new Course("Svenska 2", 100, "C"));

            CourseManager.saveStudentToFile(student);

            File file = new File(basePath.toString(), realTestSSN);

            assertTrue(file.exists());
            assertEquals("info.coolchatserver.merit.Course{name='SVENSKA 1', grade='A', coursePoints=100, gradeMerit=20.0}", CourseManager.readCourses(realTestSSN).get(0).toString());
            assertEquals("info.coolchatserver.merit.Course{name='SVENSKA 2', grade='C', coursePoints=100, gradeMerit=15.0}", CourseManager.readCourses(realTestSSN).get(1).toString());

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void readStudentFileWithComment() throws IOException, IllegalArgumentException {
        BufferedWriter bufferedWriter =  new BufferedWriter(
                new FileWriter(
                new File(basePath.toString(), realTestSSN)));
        bufferedWriter.write("SVENSKA 1, 100, A\nSVENSKA 2, 100, C\n#ENGELSKA 5, 100, D");
        bufferedWriter.close();

        Student student = new Student(realTestSSN);
        student.LoadStudentFromFile();

        assertEquals("SVENSKA 1" ,student.getCourses().get(0).getName());
        assertEquals("SVENSKA 2" ,student.getCourses().get(1).getName());

        assertThrows(IndexOutOfBoundsException.class, () -> {
            student.getCourses().get(2).getName();
        });
    }

}
