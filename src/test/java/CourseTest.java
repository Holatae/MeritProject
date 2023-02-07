import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void courseGradeATest(){
        Course course = new Course("SVENSKA 1", 100, "A");
        assertEquals(20.0, course.getGradeMerit());
    }

    @Test
    void courseGradeBTest(){
        Course course = new Course("SVENSKA 1", 100, "B");
        assertEquals(17.5, course.getGradeMerit());
    }

    @Test
    void courseGradeCTest(){
        Course course = new Course("SVENSKA 1", 100, "C");
        assertEquals(15, course.getGradeMerit());
    }

    @Test
    void courseGradeDTest(){
        Course course = new Course("SVENSKA 1", 100, "D");
        assertEquals(12.5, course.getGradeMerit());
    }

    @Test
    void courseGradeETest(){
        Course course = new Course("SVENSKA 1", 100, "E");
        assertEquals(10, course.getGradeMerit());
    }

    @Test
    void courseGradeFTest(){
        Course course = new Course("SVENSKA 1", 100, "F");
        assertEquals(0, course.getGradeMerit());
    }

    @Test
    void courseGradeANotAbsenceTest(){
        Course course = new Course("SVENSKA 1", 100, "-");
        assertEquals(0, course.getGradeMerit());
    }

    @Test
    void courseGradeIllegalArgumentTest(){
        assertThrows(NullPointerException.class, () -> {
            Course course = new Course("SVENSKA 1", 100, null);
        });
    }

    @Test
    void getCoursePoints(){
        assertEquals(100, new Course("SVENSKA 1", 100, "A").getCoursePoints());
    }

    @Test
    void getTotalMerit(){
        assertEquals(3000, new Course("FYSIK 1", 150, "A").getTotalMerit());
    }

}
