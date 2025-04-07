package util;

import presentation.UniversityManager;

import java.util.Scanner;

public class CourseValidator {
    public static String validateCourseId (Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            try {
                String courseId = scanner.nextLine().trim();

                if (courseId.trim().isEmpty()) {
                    throw new IllegalArgumentException("Mã khóa học không được để trống.");
                }

                if (!courseId.matches("(C)\\w{4}$")) {
                    throw new IllegalArgumentException("Dữ liệu không hợp lệ, vui lòng nhập lại");
                }

                boolean isDuplicate = UniversityManager.courseList.stream().anyMatch(course -> course.getCourseId() != null && course.getCourseId().equalsIgnoreCase(courseId));
                if (isDuplicate) {
                    throw new IllegalArgumentException("Mã khóa học đã tồn tại. Vui lòng nhập mã khác");
                }


                return courseId;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
