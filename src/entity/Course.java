package entity;

import util.CourseValidator;
import util.StringRule;
import util.Validator;

import java.util.Scanner;

public class Course implements IApp {
    private String courseId;
    private String courseName;
    private boolean status;

    public Course() {
    }

    public Course(String courseId, String courseName, boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.courseId =  CourseValidator.validateCourseId(scanner,"Nhập vào mã khóa học: ");
        this.courseName =  Validator.validateInputString(scanner, "Nhập vào tên khóa học: ", new StringRule(20, 100));
        this.status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái khóa học: ");
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", status=" + status +
                '}';
    }
}
