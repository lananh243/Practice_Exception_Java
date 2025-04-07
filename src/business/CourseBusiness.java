package business;

import entity.Course;
import presentation.UniversityManager;
import util.StringRule;
import util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class CourseBusiness {
    public static void sortByNameAscending() {
        if (UniversityManager.courseList.isEmpty()) {
            System.err.println("Chưa có khóa học");
            return;
        }

        UniversityManager.courseList.stream()
                .sorted(Comparator.comparing(Course::getCourseName))
                .forEach(System.out::println);
    }


    public static void addNewCourse(Scanner scanner) {
        int numberOfCourse = Validator.validateInputInteger(scanner, "Nhập số khóa học cần nhập thông tin: ");
        for (int i = 0; i < numberOfCourse; i++) {
            Course course = new Course();
            course.inputData(scanner);
            UniversityManager.courseList.add(course);
        }
    }

    public static void updateCourse(Scanner scanner) {
        System.out.println("Nhập và mã khóa học cần cập nhật: ");
        String courseId = scanner.nextLine().trim();
        int indexUpdate = findIndexById(courseId);
        if (indexUpdate == -1) {
            System.err.println("Không tồn tại mã khóa học");
            return;
        }

        do {
            System.out.println("1. Cập nhật tên khóa học");
            System.out.println("2. Cập nhật trạng thái khóa học");
            System.out.println("3. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    String courseName = Validator.validateInputString(scanner, "Nhập tên khóa học cần cập nhật: ", new StringRule(20, 100));
                    UniversityManager.courseList.get(indexUpdate).setCourseName(courseName);
                    break;
                case 2:
                    boolean status = Validator.validateInputBoolean(scanner, "Nhập vào trạng thái cần cập nhật: ");
                    UniversityManager.courseList.get(indexUpdate).setStatus(status);
                    break;
                case 3:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 3");
            }
        } while (true);

    }

    public static int findIndexById(String courseId) {
        for (int i = 0; i < UniversityManager.courseList.size(); i++) {
            if (courseId.equalsIgnoreCase(UniversityManager.courseList.get(i).getCourseName())) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteCourse(Scanner scanner) {
        System.out.print("Nhập và mã khóa học cần xóa: ");
        String courseId = scanner.nextLine().trim();
        int indexDelete = findIndexById(courseId);
        if (indexDelete == -1) {
            System.err.println("Mã khóa học không tồn tại");
            return;
        }

        boolean hasClassRoom = UniversityManager.listClassRoom.stream()
                .anyMatch(classRoom -> classRoom.getCourseId().equalsIgnoreCase(courseId));

        if (hasClassRoom) {
            System.err.println("Không thể xóa khóa học vì đã có lớp học");
            return;
        }

        UniversityManager.courseList.remove(indexDelete);
        System.out.println("Đã xóa khóa học thành công");
    }
}
