package business;

import entity.Student;
import presentation.UniversityManager;
import util.StudentValidator;
import util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class StudentBusiness {
    public static void sortByNameAscending() {
        if (UniversityManager.studentList.isEmpty()) {
            System.err.println("Danh sách sinh viên trống");
            return;
        }

        UniversityManager.studentList.stream().sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);
    }

    public static void addNewStudent(Scanner scanner) {
       int numberOfStudent = Validator.validateInputInteger(scanner, "Nhập vào số sinh viên cần nhập thông tin: ");
       for (int i = 0; i < numberOfStudent; i++) {
           Student student = new Student();
           student.inputData(scanner);
           UniversityManager.studentList.add(student);
       }
    }

    public static void updateStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật: ");
        String studentId = scanner.nextLine().trim();

        int indexUpdate = findIndexById(studentId);
        if (indexUpdate == -1) {
            System.err.println("Không tồn tại mã sinh viên");
            return;
        }

        do {
            System.out.println("1. Cập nhật điểm trung bình học tập của sinh viên");
            System.out.println("2. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    double gpa = StudentValidator.validateInputDouble("Nhập vào điểm trung bình học tập của sinh viên cần cập nhật: ", scanner);
                    UniversityManager.studentList.get(indexUpdate).setGpa(gpa);
                    break;
                case 2:
                    return;
            }
        } while (true);
    }

    public static int findIndexById(String studentId) {
        for (int i = 0; i < UniversityManager.studentList.size(); i++) {
            if (UniversityManager.studentList.get(i).getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteStudent(Scanner scanner) {
        System.out.println("Nhập vào mã sinh viên cần cập nhật: ");
        String studentId = scanner.nextLine().trim();

        int indexDelete = findIndexById(studentId);
        if (indexDelete == -1) {
            System.err.println("Không tồn tại mã sinh viên");
            return;
        }

        boolean isCheckStudentCourseRegistration = UniversityManager.listCourseRegistration.stream()
                .anyMatch(courseRegistration -> courseRegistration.getStudentId().equalsIgnoreCase(studentId));

        if (isCheckStudentCourseRegistration) {
            System.err.println("Không thể xóa sinh viên vì sinh viên đã đăng ký khóa học");
            return;
        }

        UniversityManager.studentList.remove(indexDelete);
        System.out.println("Đã xóa sinh viên thành công");
    }
}
