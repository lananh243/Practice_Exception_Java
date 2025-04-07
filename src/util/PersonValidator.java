package util;

import entity.Person;
import presentation.UniversityManager;

import java.util.Scanner;

public class PersonValidator {

    public static String validatePersonName(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String name = scanner.nextLine();
                if (name.isEmpty()) {
                    throw new IllegalArgumentException("Tên không được để trống");
                }

                if (name.length() < 0 ||name.length() > 150) {
                    throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
                }

                return name;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static int validatePersonAge(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                int age = Integer.parseInt(scanner.nextLine());
                if (age < 18) {
                    throw new IllegalArgumentException("Tuổi phải trên 18 tuổi");
                }
                return age;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static String validatePersonAddress(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String address = scanner.nextLine();
                if (address.isEmpty()) {
                    throw new IllegalArgumentException("Dữ liệu không được để trống");
                }
                return address;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validatePersonPhone(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String phone = scanner.nextLine();
                if (phone.isEmpty()) {
                    throw new IllegalArgumentException("Số điện thoại không được để trống");
                }

                if (phone.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$")) {
                    throw new IllegalArgumentException("Định dạng số điện thoại ko hợp lệ, vui lòng nhập lại");
                }

                boolean isDuplicate = UniversityManager.listPerson.stream().anyMatch(person -> person.getPhone().equalsIgnoreCase(phone));
                if (!isDuplicate) {
                    throw new IllegalArgumentException("Số điện thoại đã tồn tại, vui lòng nhập lại");
                }
                return phone;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String validatePersonEmail(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String email = scanner.nextLine();
                if (email.isEmpty()) {
                    throw new IllegalArgumentException("Email không được để trống");
                }

                if (!email.matches("^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$")) {
                    throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
                }

                boolean isDuplicate = UniversityManager.listPerson.stream().anyMatch(person -> person.getEmail().equals(email));
                if (isDuplicate) {
                    throw new IllegalArgumentException("Email đã tồn tại, vui lòng nhập lại");
                }
                return email;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
