package entity;

import util.TeacherValidator;

import java.util.Scanner;

public class Teacher extends Person implements IApp {
    private static int idAutocreasement = 0;
    private int teacherId;
    private String subject;

    public Teacher() {
        super();
        teacherId = ++idAutocreasement;
    }

    public Teacher(String name, int age, String address, String phone, String email, Sex sex, String subject) {
        super(name, age, address, phone, email, sex);
        this.teacherId = ++idAutocreasement;
        this.subject = subject;
    }

    public int getTeacherId() {
        return teacherId;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.subject = TeacherValidator.validateSubject(scanner, "Nhập vào chuyên môn của giảng viên: ");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", subject='" + subject + '\'' +
                '}';
    }
}
