package util;

import presentation.UniversityManager;

import java.util.Scanner;

public class StudentValidator {

    public static String validateStudentId(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String studentId = scanner.nextLine();
                if (!studentId.matches("(SV)\\w{3}$")){
                    throw new IllegalArgumentException("Dữ liệu sai định dạng, vui lòng nhập lại");
                }

                boolean isDuplicate = UniversityManager.studentList.stream().anyMatch(student -> student.getStudentId().equals(studentId));
                if (isDuplicate) {
                    throw new IllegalArgumentException("Mã sinh viên đã tồn tại, vui lòng nhập lại");
                }
                return studentId;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static double validateInputDouble(String message, Scanner scanner) {
        System.out.println(message);
        do {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.err.println("Dữ liệu nhập không hợp lệ, vui lòng nhập lại");
            }
        } while (true);
    }
}
