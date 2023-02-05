import org.jetbrains.annotations.NotNull;

public class Course {
    private String name;
    private String grade;

    public int getCoursePoints() {
        return coursePoints;
    }

    public void setCoursePoints(int coursePoints) {
        this.coursePoints = coursePoints;
    }

    private int coursePoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getGradeMerit() {
        return gradeMerit;
    }

    public void setGradeMerit(double gradeMerit) {
        this.gradeMerit = gradeMerit;
    }

    private double gradeMerit;

    public Course(@NotNull String name, int coursePoints, @NotNull String grade) {
        this.name = name;
        this.grade = grade;
        this.coursePoints = coursePoints;

        switch (grade.toUpperCase()){
            case "A" -> {
                this.gradeMerit = 20;
            }
            case "B" -> {
                this.gradeMerit = 17.5;
            }
            case "C" -> {
                this.gradeMerit = 15;
            }
            case "D" -> {
                this.gradeMerit = 12.5;
            }
            case "E" -> {
                this.gradeMerit = 10;
            }
            case "F", "-" -> {
                this.gradeMerit = 0;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", grade='" + grade + '\'' +
                ", coursePoints=" + coursePoints +
                ", gradeMerit=" + gradeMerit +
                '}';
    }
}
