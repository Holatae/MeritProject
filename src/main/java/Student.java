import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import dev.personnummer.*;

public class Student {
    private String SSN;
    private ArrayList<Course> courses = new ArrayList<>();

    public Student(String SSN, ArrayList<Course> courses) throws PersonnummerException {
        this.SSN = new Personnummer(SSN).format();
        File file = new File("students", SSN);
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

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) throws PersonnummerException, IOException {
        this.courses.add(course);
        //ReadGrades.appendCourse(course, this.getSSN());
    }
}
