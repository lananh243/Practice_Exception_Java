package entity;

import presentation.UniversityManager;

import java.time.LocalDate;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CourseRegistration implements IApp {
    private static int idAutocreasement = 0;
    private int crid;
    private String studentId;
    private String courseId;
    private LocalDate registrationDate;
    private StatusCourse status;

    public CourseRegistration(String studentId, String courseId, StatusCourse pending) {
    }

    public CourseRegistration(String studentId, String courseId, LocalDate registrationDate, StatusCourse status) {
        this.crid = ++idAutocreasement;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationDate = LocalDate.now();
        this.status = status;
    }

    public int getCrid() {
        return crid;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public StatusCourse getStatus() {
        return status;
    }

    public void setStatus(StatusCourse status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.studentId = choiceStudentId(scanner);
        this.courseId = choiceCourseId(scanner);
    }

    private String choiceStudentId(Scanner scanner) {
        System.out.println("Chọn mã sinh viên: ");
        while (true) {
            try {
                if (UniversityManager.studentList.isEmpty()) {
                    return null;
                }
                UniversityManager.studentList.forEach(student -> System.out.printf("%s", student.getStudentId()));
                System.out.println("Lựa chọn của bạn: ");
                String choiceId = scanner.nextLine();
                OptionalInt indexStudent = IntStream.range(0, UniversityManager.studentList.size())
                        .filter(index -> UniversityManager.studentList.get(index).getStudentId().equals(choiceId)).findFirst();

                if (indexStudent.isPresent()) {
                    return UniversityManager.studentList.get(indexStudent.getAsInt()).getStudentId();
                }
                throw new IllegalArgumentException("Vui lòng chọn đúng mã sinh viên");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private String choiceCourseId(Scanner scanner) {
        System.out.println("Chọn mã khóa học: ");
        while (true) {
            try {
                if (UniversityManager.courseList.isEmpty()) {
                    return null;
                }
                UniversityManager.courseList.forEach(course -> System.out.printf("%s", course.getCourseId()));
                System.out.println("Lựa chọn của bạn: ");
                String choiceId = scanner.nextLine();
                OptionalInt indexCourse = IntStream.range(0, UniversityManager.courseList.size())
                        .filter(index -> UniversityManager.courseList.get(index).getCourseId().equals(choiceId)).findFirst();
                if (indexCourse.isPresent()) {
                    return UniversityManager.courseList.get(indexCourse.getAsInt()).getCourseId();
                }
                throw new IllegalArgumentException("Vui lòng chọn đúng mã khóa học");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "CourseRegistration{" +
                "crid=" + crid +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", registrationDate=" + registrationDate +
                ", status=" + status +
                '}';
    }
}
