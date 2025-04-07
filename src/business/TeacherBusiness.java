package business;

import entity.Course;
import entity.Teacher;
import presentation.UniversityManager;
import util.TeacherValidator;
import util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class TeacherBusiness {
    public static void sortByIdDescending() {
        if (UniversityManager.teacherList.isEmpty()) {
            System.err.println("Danh sách giảng viên trống");
            return;
        }
        UniversityManager.teacherList.stream().sorted(Comparator.comparing(Teacher::getTeacherId).reversed())
                .forEach(System.out::println);
    }

    public static void addNewTeacher(Scanner scanner) {
        int numberOfCourse = Validator.validateInputInteger(scanner, "Nhập số giảng viên cần nhập thông tin: ");
        for (int i = 0; i < numberOfCourse; i++) {
            Teacher teacher = new Teacher();
            teacher.inputData(scanner);
            UniversityManager.teacherList.add(teacher);
        }
    }


    public static int findIndexById(int teacherId) {
        for (int i = 0; i < UniversityManager.teacherList.size(); i++) {
            if (UniversityManager.teacherList.get(i).getTeacherId() == teacherId) {
                return i;
            }
        }
        return -1;
    }

    public static void updateTeacher(Scanner scanner) {
        System.out.println("Nhập vào mã giảng viên cần cập nhật: ");
        int teacherId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexById(teacherId);
        if (indexUpdate == -1) {
            System.err.println("Không tồn tại mã giảng viên");
            return;
        }
        do {
            System.out.println("1. Cập nhật chuyên môn giảng viên: ");
            System.out.println("2. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    String subject = TeacherValidator.validateSubject(scanner, "Nhập vào chuyên môn giảng viên cần cập nhật: ");
                    UniversityManager.teacherList.get(indexUpdate).setSubject(subject);
                    break;
                case 2:
                    return;
                default:
                    System.err.println("Vui lòng chọn 1 - 2");
            }
        } while (true);
    }

    public static void deleteTeacher(Scanner scanner) {
        System.out.println("Nhập vào mã giảng viên cần xóa: ");
        int teacherId = Integer.parseInt(scanner.nextLine());
        int indexDelete = findIndexById(teacherId);
        if (indexDelete == -1) {
            System.err.println("Không tồn tại mã giảng viên");
            return;
        }

        boolean isCheckClassroom = UniversityManager.listClassRoom.stream()
                .anyMatch(classRoom -> classRoom.getClassRoomId() == teacherId);

        if (isCheckClassroom) {
            System.err.println("Không thể xóa giảng viên vì giảng viên đã được xếp lớp");
            return;
        }

        UniversityManager.teacherList.remove(indexDelete);
        System.out.println("Xóa giảng viên thành công");
    }
}
