package entity;

import util.StudentValidator;

import java.util.Scanner;

public class Student extends Person implements IApp {
    private String studentId;
    private double gpa;


    public Student() {
        super();
    }

    public Student(String name, int age, String address, String phone, String email, Sex sex, String studentId, double gpa) {
        super(name, age, address, phone, email, sex);
        this.studentId = studentId;
        this.gpa = gpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.studentId = StudentValidator.validateStudentId(scanner, "Nhập vào mã sinh viên: ");
        this.gpa = StudentValidator.validateInputDouble("Nhập vào điểm trung bình của sinh viên: ", scanner);
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
