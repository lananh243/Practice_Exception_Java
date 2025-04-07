package presentation;

import business.*;
import entity.*;
import util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityManager {
    public static final List<Student> studentList = new ArrayList<>();
    public static final List<Course> courseList = new ArrayList<>();
    public static final List<Teacher> teacherList = new ArrayList<>();
    public static final List<CourseRegistration> listCourseRegistration = new ArrayList<>();
    public static final List<ClassRoom> listClassRoom = new ArrayList<>();
    public static final List<Person> listPerson = new ArrayList<>();
    public static final List<Schedule> listSchedule = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************************* UNIVERSITY MENU **********************");
            System.out.println("1. Quản lý khóa học");
            System.out.println("2. Quản lý giảng viên");
            System.out.println("3. Quản lý sinh viên");
            System.out.println("4. Đăng ký khóa học");
            System.out.println("5. Quản lý lớp học");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn từ 1 - 7: ");
            switch (choice) {
                case 1:
                    displayCourseMenu(scanner);
                    break;
                case 2:
                    displayTeacherMenu(scanner);
                    break;
                case 3:
                    displayStudentMenu(scanner);
                    break;
                case 4:
                    displayCourseRegistrationMenu(scanner);
                    break;
                case 5:
                    displayClassRoomMenu(scanner);
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1 - 7");
            }
        } while (true);
    }

    public static void displayCourseMenu(Scanner scanner) {
        do {
            System.out.println("************************* COURSE MENU *************************");
            System.out.println("1. Danh sách khóa học sắp xếp theo tên tăng dần");
            System.out.println("2. Thêm mới khóa học");
            System.out.println("3. Cập nhật khóa học");
            System.out.println("4. Xóa khóa học theo ID");
            System.out.println("5. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn từ 1 - 5: ");
            switch (choice) {
                case 1:
                    CourseBusiness.sortByNameAscending();
                    break;
                case 2:
                    CourseBusiness.addNewCourse(scanner);
                    break;
                case 3:
                    CourseBusiness.updateCourse(scanner);
                    break;
                case 4:
                    CourseBusiness.deleteCourse(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 5");
            }
        } while (true);
    }

    public static void displayTeacherMenu(Scanner scanner) {
        do {
            System.out.println("************************* TEACHER MENU *************************");
            System.out.println("1. Danh sách giảng viên sắp xếp theo mã giảm dần");
            System.out.println("2. Thêm mới giảng viên");
            System.out.println("3. Cập nhật giảng viên");
            System.out.println("4. Xóa giảng viên");
            System.out.println("5. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn từ 1 - 5: ");
            switch (choice) {
                case 1:
                    TeacherBusiness.sortByIdDescending();
                    break;
                case 2:
                    TeacherBusiness.addNewTeacher(scanner);
                    break;
                case 3:
                    TeacherBusiness.updateTeacher(scanner);
                    break;
                case 4:
                    TeacherBusiness.deleteTeacher(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 5");
            }
        } while (true);
    }

    public static void displayStudentMenu(Scanner scanner) {
        do {
            System.out.println("************************* STUDENT MENU *************************");
            System.out.println("1. Danh sách sinh viên được sắp xếp theo tên tăng dần");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    StudentBusiness.sortByNameAscending();
                    break;
                case 2:
                    StudentBusiness.addNewStudent(scanner);
                    break;
                case 3:
                    StudentBusiness.updateStudent(scanner);
                    break;
                case 4:
                    StudentBusiness.deleteStudent(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 5");
            }
        } while (true);
    }

    public static void displayCourseRegistrationMenu(Scanner scanner) {
        do {
            System.out.println("*******************COURSE REGISTRATION MENU*****************");
            System.out.println("1. Đăng ký khóa học cho sinh viên");
            System.out.println("2. Hủy đăng ký học cho sinh viên");
            System.out.println("3. Duyệt đăng ký học sinh viên");
            System.out.println("4. Xem danh sách sinh viên đăng ký từng khóa học");
            System.out.println("5. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chon của bạn: ");
            switch (choice) {
                case 1:
                    CourseRegistrationBusiness.registerCourseForStudent(scanner);
                    break;
                case 2:
                    CourseRegistrationBusiness.cancelCourseRegistration(scanner);
                    break;
                case 3:
                    CourseRegistrationBusiness.approveCourseRegistration(scanner);
                    break;
                case 4:
                    CourseRegistrationBusiness.viewStudentListForCourse(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 5");
            }
        } while (true);
    }

    public static void displayClassRoomMenu(Scanner scanner) {
        do {
            System.out.println("********************** CLASS ROOM MENU **********************");
            System.out.println("1. Danh sách lớp học sắp xếp theo ngày tạo giảm dần");
            System.out.println("2. Thêm mới lớp học");
            System.out.println("3. Cập nhật thông tin lớp học");
            System.out.println("4. Xóa lớp học");
            System.out.println("5. Phân công giảng viên cho lớp");
            System.out.println("6. Thêm sinh viên vào cho lớp");
            System.out.println("7. Tạo lịch học cho lớp");
            System.out.println("8. Cập nhật trậng thái lớp");
            System.out.println("9. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn: ");
            switch (choice) {
                case 1:
                    ClassroomBusiness.sortByCreatedDescending();
                    break;
                case 2:
                    ClassroomBusiness.addNewClassroom(scanner);
                    break;
                case 3:
                    ClassroomBusiness.updateClassroom(scanner);
                    break;
                case 4:
                    ClassroomBusiness.deleteClassroom(scanner);
                    break;
                case 5:
                    ClassroomBusiness.assignTeacherToClassroom(scanner);
                    break;
                case 6:
                    ClassroomBusiness.addStudentToClassroom(scanner);
                    break;
                case 7:
                    ClassroomBusiness.createSchedule(scanner);
                    break;
                case 8:
                    ClassroomBusiness.updateClassroomStatus(scanner);
                    break;
                case 9:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 9");
            }
        } while (true);
    }
}
