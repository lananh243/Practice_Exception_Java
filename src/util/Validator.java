package util;

import presentation.UniversityManager;

import java.util.Scanner;

public class Validator {

    public static String validateInputString(Scanner scanner, String message, StringRule stringRule) {
        while (true) {
            System.out.println(message);
            try {
                String inputString = scanner.nextLine().trim();
                if (inputString.isEmpty()) {
                    throw new IllegalArgumentException("Tên không được để trống.");
                }

                if (!stringRule.isValidString(inputString)) {
                    throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
                }

                boolean isDuplicateCourse = UniversityManager.courseList.stream()
                        .anyMatch(course -> course.getCourseName() != null && course.getCourseName().equalsIgnoreCase(inputString));
                if (isDuplicateCourse) {
                    throw new IllegalArgumentException("Tên khóa học đã tồn tại, vui lòng nhập tên khác.");
                }

                boolean isDuplicateClassroom = UniversityManager.listClassRoom.stream().anyMatch(classRoom -> classRoom.getClassRoomName() != null && classRoom.getClassRoomName().equalsIgnoreCase(inputString));
                if (isDuplicateClassroom) {
                    throw new IllegalArgumentException("Tên lớp học đã tồn tại, vui lòng nhập tên khác.");
                }

                return inputString;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean validateInputBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String inputString = scanner.nextLine();
                if (inputString.equalsIgnoreCase("true") || inputString.equalsIgnoreCase("false")) {
                    return Boolean.parseBoolean(inputString);
                }

                throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validateInputInteger(Scanner scanner, String message) {
        System.out.println(message);
        do {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.err.println("Dữ liệu nhập vào không phải số nguyên, vui lòng nhập lại");
            }
        } while (true);
    }
}
