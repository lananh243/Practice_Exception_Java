package util;

import java.util.Scanner;

public class TeacherValidator {
    public static String validateSubject(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String subject = scanner.nextLine();
                if (subject.isEmpty()) {
                    throw new IllegalArgumentException("Chuyên môn giảng viên ko đc để trống");
                }
                return subject;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
