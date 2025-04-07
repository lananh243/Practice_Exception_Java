package entity;

import util.PersonValidator;

import java.util.Scanner;

public class Person {
    private String name;
    private int age;
    private String address;
    private String phone;
    private String email;
    private Sex sex;


    public Person(String name, int age, String address, String phone, String email, Sex sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void inputData(Scanner scanner) {
        PersonValidator.validatePersonName(scanner, "Nhập vào tên: ");
        PersonValidator.validatePersonAge(scanner, "Nhập vào tuổi: ");
        PersonValidator.validatePersonAddress(scanner, "Nhập vào địa chỉ: ");
        PersonValidator.validatePersonPhone(scanner, "Nhập vào số điện thoại: ");
        PersonValidator.validatePersonEmail(scanner, "Nhập vào email: ");
        System.out.println("Chọn giới tính: ");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("Giới tính khác");
        System.out.println("Lựa chọn của bạn: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                this.sex = Sex.MALE;
                break;
            case 2:
                this.sex = Sex.FEMALE;
                break;
            default:
                this.sex = Sex.OTHER;
        }
    }
}
