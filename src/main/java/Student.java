import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import dev.personnummer.*;

public class Student {
    private final String SSN;
    private final ArrayList<Course> courses = new ArrayList<>();

    /**
     * @param SSN Social security number
     * @param courses if you don't currently have any courses in memory, just set it to <strong>null</strong>
     * @throws PersonnummerException If the SSN is invalid an exception is thrown.
     */
    public Student(String SSN, ArrayList<Course> courses) throws PersonnummerException {
        this.SSN = new Personnummer(SSN).format();
        File file = new File("students", this.SSN);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "SSN='" + SSN + '\'' +
                ", courses=" + courses +
                '}';
    }

    public String getSSN() {
        return SSN;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * @param course the course you want to add to current student
     * @see Course
     */
    public void addCourse(Course course) throws PersonnummerException {
        this.courses.add(course);
        //ReadGrades.appendCourse(course, this.getSSN());
    }
}
