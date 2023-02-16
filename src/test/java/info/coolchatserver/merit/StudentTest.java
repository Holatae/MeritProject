package info.coolchatserver.merit;

import dev.personnummer.PersonnummerException;
import info.coolchatserver.merit.Course;
import info.coolchatserver.merit.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentTest {
    private final String realTestSSN = "690813-3272";

    @Test
    void studentAddingCoursesTest() throws PersonnummerException {
        Student student = new Student(realTestSSN);
        student.addCourse(new Course("SVENSKA 1", 100, "A"));
        student.addCourse(new Course("PROGRAMMERING 1", 100, "E"));

        assertEquals("info.coolchatserver.merit.Course{name='SVENSKA 1', grade='A', coursePoints=100, gradeMerit=20.0}", student.getCourses().get(0).toString());
        assertEquals("info.coolchatserver.merit.Course{name='PROGRAMMERING 1', grade='E', coursePoints=100, gradeMerit=10.0}", student.getCourses().get(1).toString());
    }

    @Test
    void createStudentWithoutLegalSSN(){
        assertThrows(PersonnummerException.class, () -> new Student("1111111111"));
    }

}
