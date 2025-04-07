package business;

import entity.Course;
import entity.CourseRegistration;
import entity.StatusCourse;
import entity.Student;
import presentation.UniversityManager;
import util.CourseValidator;
import util.StudentValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseRegistrationBusiness {
    public static void registerCourseForStudent(Scanner scanner) {
        String studentId = StudentValidator.validateStudentId(scanner, "Nhập vào mã sinh viên: ");

        Optional<Student> studentOpt = UniversityManager.studentList.stream()
                .filter(student -> studentId.equalsIgnoreCase(student.getStudentId())).findFirst();

        if (!studentOpt.isPresent()) {
            System.err.println("Không tìm thấy sinh viên với mã: " + studentId);
            return;
        }

        String courseId = CourseValidator.validateCourseId(scanner,"Nhập vào mã khóa học: ");
        Optional<Course> courseOpt = UniversityManager.courseList.stream()
                .filter(course -> courseId.equalsIgnoreCase(course.getCourseId())).findFirst();

        if (!courseOpt.isPresent()) {
            System.err.println("Không tìm thấy khóa học với mã: " + courseId);
            return;
        }

        boolean alreadyRegistered = UniversityManager.listCourseRegistration.stream()
                .anyMatch(courseReg -> courseReg.getStudentId().equalsIgnoreCase(studentId) &&
                        courseReg.getCourseId().equalsIgnoreCase(courseId));

        if (alreadyRegistered) {
            System.err.println("Sinh viên đã đăng ký khóa học này");
            return;
        }

        CourseRegistration newRegistration = new CourseRegistration(studentId, courseId, StatusCourse.PENDING);
        UniversityManager.listCourseRegistration.add(newRegistration);
        System.out.println("Đăng ký khóa học thành công!");

    }

    public static void cancelCourseRegistration(Scanner scanner) {
        String studentId = StudentValidator.validateStudentId(scanner, "Nhập vào mã sinh viên: ");

        Optional<Student> studentOpt = UniversityManager.studentList.stream()
                .filter(student -> studentId.equalsIgnoreCase(student.getStudentId())).findFirst();

        if (!studentOpt.isPresent()) {
            System.err.println("Không tìm thấy sinh viên với mã: " + studentId);
            return;
        }

        String courseId = CourseValidator.validateCourseId(scanner, "Nhập vào mã khóa học: ");

        Optional<Course> courseOpt = UniversityManager.courseList.stream()
                .filter(course -> courseId.equalsIgnoreCase(course.getCourseId())).findFirst();

        if (!courseOpt.isPresent()) {
            System.err.println("Không tìm thấy khóa học với mã: " + courseId);
            return;
        }

        Optional<CourseRegistration> registrationOpt = UniversityManager.listCourseRegistration.stream()
                .filter(registration -> registration.getStudentId().equalsIgnoreCase(studentId)
                        && registration.getCourseId().equalsIgnoreCase(courseId))
                .findFirst();

        if (!registrationOpt.isPresent()) {
            System.err.println("Sinh viên chưa đăng ký khóa học này.");
            return;
        }

        CourseRegistration registration = registrationOpt.get();
        if (registration.getStatus() != StatusCourse.PENDING) {
            System.err.println("Không thể hủy đăng ký vì trạng thái không phải PENDING.");
            return;
        }

        UniversityManager.listCourseRegistration.remove(registration);
        System.out.println("Đã hủy đăng ký khóa học thành công.");
    }


    public static void approveCourseRegistration(Scanner scanner) {
        String studentId = StudentValidator.validateStudentId(scanner, "Nhập vào mã sinh viên: ");

        Optional<Student> studentOpt = UniversityManager.studentList.stream()
                .filter(student -> studentId.equalsIgnoreCase(student.getStudentId())).findFirst();

        if (!studentOpt.isPresent()) {
            System.err.println("Không tìm thấy sinh viên với mã: " + studentId);
            return;
        }

        String courseId = CourseValidator.validateCourseId(scanner, "Nhập vào mã khóa học: ");

        Optional<Course> courseOpt = UniversityManager.courseList.stream()
                .filter(course -> courseId.equalsIgnoreCase(course.getCourseId())).findFirst();

        if (!courseOpt.isPresent()) {
            System.err.println("Không tìm thấy khóa học với mã: " + courseId);
            return;
        }

        Optional<CourseRegistration> registrationOpt = UniversityManager.listCourseRegistration.stream()
                .filter(registration -> registration.getStudentId().equalsIgnoreCase(studentId)
                        && registration.getCourseId().equalsIgnoreCase(courseId))
                .findFirst();

        if (!registrationOpt.isPresent()) {
            System.err.println("Sinh viên chưa đăng ký khóa học này.");
            return;
        }

        CourseRegistration registration = registrationOpt.get();
        if (registration.getStatus() != StatusCourse.PENDING) {
            System.err.println("Đăng ký không phải là PENDING, không thể duyệt.");
            return;
        }

        registration.setStatus(StatusCourse.ENROLLED);
        System.out.println("Đăng ký khóa học đã được duyệt và sinh viên đã được ghi danh.");
    }


    public static void viewStudentListForCourse(Scanner scanner) {
        String courseId = CourseValidator.validateCourseId(scanner, "Nhập vào mã khóa học: ");

        Optional<Course> courseOpt = UniversityManager.courseList.stream()
                .filter(course -> courseId.equalsIgnoreCase(course.getCourseId()))
                .findFirst();

        if (!courseOpt.isPresent()) {
            System.err.println("Không tìm thấy khóa học với mã: " + courseId);
            return;
        }

        List<CourseRegistration> registrationsForCourse = UniversityManager.listCourseRegistration.stream()
                .filter(registration -> registration.getCourseId().equalsIgnoreCase(courseId))
                .collect(Collectors.toList());

        if (registrationsForCourse.isEmpty()) {
            System.out.println("Chưa có sinh viên nào đăng ký khóa học này.");
            return;
        }

        System.out.println("Danh sách sinh viên đã đăng ký khóa học " + courseId + ":");
        for (CourseRegistration registration : registrationsForCourse) {
            String studentId = registration.getStudentId();
            Optional<Student> studentOpt = UniversityManager.studentList.stream()
                    .filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
                    .findFirst();

            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                System.out.println("Mã sinh viên: " + student.getStudentId() + ", Tên sinh viên: " + student.getName() + ", Trạng thái: " + registration.getStatus());
            }
        }
    }
}
