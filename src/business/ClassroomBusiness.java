package business;

import entity.*;
import presentation.UniversityManager;
import util.StringRule;
import util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class ClassroomBusiness {

    public static void sortByCreatedDescending() {
        if (UniversityManager.listClassRoom.isEmpty()) {
            System.err.println("Danh sách lớp trống");
            return;
        }

        UniversityManager.listClassRoom.stream()
                .sorted(Comparator.comparing(ClassRoom::getCreated).reversed())
                .forEach(System.out::println);
    }

    public static void addNewClassroom(Scanner scanner) {
        int numberOfCourse = Validator.validateInputInteger(scanner, "Nhập số lớp học cần nhập thông tin: ");
        for (int i = 0; i < numberOfCourse; i++) {
            ClassRoom classRoom = new ClassRoom();
            classRoom.inputData(scanner);
            UniversityManager.listClassRoom.add(classRoom);
        }
    }

    public static int findIndexById(int classRoomId) {
        for (int i = 0; i < UniversityManager.listClassRoom.size(); i++) {
            if (UniversityManager.listClassRoom.get(i).getClassRoomId() == classRoomId) {
                return i;
            }
        }
        return -1;
    }

    public static void updateClassroom(Scanner scanner) {
        System.out.println("Nhập vào mã lớp học cần cập nhật: ");
        int classRoomId = Integer.parseInt(scanner.nextLine());
        int indexUpdate = findIndexById(classRoomId);
        if (indexUpdate == -1) {
            System.err.println("Không tồn tại mã lớp học");
            return;
        }

        Optional<ClassRoom> classRoomOpt = UniversityManager.listClassRoom.stream()
                .filter(classRoom -> classRoom.getClassRoomId() == classRoomId)
                .findFirst();

        if (!classRoomOpt.isPresent()) {
            System.err.println("Lớp học không tồn tại với mã: " + classRoomId);
            return;
        }

        ClassRoom classRoom = classRoomOpt.get();
        if (classRoom.getStatus() == StatusClassRoom.CLOSE) {
            System.err.println("Không thể cập nhật thông tin lớp học vì lớp học đã đóng.");
            return;
        }

        do {
            System.out.println("1. Cập nhật tên lớp học");
            System.out.println("2. Cập nhật ngày tạo");
            System.out.println("3. Cập nhật trạng thái lớp học");
            System.out.println("4. Thoát");
            int choice = Validator.validateInputInteger(scanner, "Lựa chọn của bạn");
            switch (choice) {
                case 1:
                    String classRoomName = Validator.validateInputString(scanner, "Nhập vào tên lớp học: ", new StringRule(15, 50));
                    UniversityManager.listClassRoom.get(indexUpdate).setClassRoomName(classRoomName);
                    break;
                case 2:
                    LocalDate created = inputCreatedDate(scanner);
                    UniversityManager.listClassRoom.get(indexUpdate).setCreated(created);
                    break;
                case 3:
                    System.out.println("Nhập trạng thái lớp học: ");
                    String statusInput = scanner.nextLine().toUpperCase();
                    try {
                        StatusClassRoom status = StatusClassRoom.valueOf(statusInput);
                        UniversityManager.listClassRoom.get(indexUpdate).setStatus(status);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Trạng thái không hợp lệ. Vui lòng nhập lại");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1 - 4");
            }
        } while (true);
    }

    public static LocalDate inputCreatedDate(Scanner scanner) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo:");
        return LocalDate.parse(scanner.nextLine(), dtf);
    }

    public static void deleteClassroom(Scanner scanner) {
        System.out.println("Nhập vào mã lớp học cần xóa: ");
        int classRoomId = Integer.parseInt(scanner.nextLine());
        int indexDelete = findIndexById(classRoomId);

        if (indexDelete == -1) {
            System.err.println("Không tồn tại mã lớp học");
            return;
        }

        Optional<ClassRoom> classRoomOpt = UniversityManager.listClassRoom.stream()
                .filter(classRoom -> classRoom.getClassRoomId() == classRoomId)
                .findFirst();

        if (!classRoomOpt.isPresent()) {
            System.err.println("Lớp học không tồn tại với mã: " + classRoomId);
            return;
        }

        ClassRoom classRoom = classRoomOpt.get();

        boolean hasStudents = UniversityManager.listCourseRegistration.stream()
                .anyMatch(registration -> registration.getCourseId().equals(classRoom.getCourseId()));

        boolean hasTeachers = UniversityManager.listClassRoom.stream()
                .anyMatch(room -> room.getClassRoomId() == classRoomId && room.getTeacherId() != 0);

        if (hasStudents) {
            System.err.println("Không thể xóa lớp học vì lớp học có sinh viên đăng ký.");
            return;
        }

        if (hasTeachers) {
            System.err.println("Không thể xóa lớp học vì lớp học đã có giảng viên.");
            return;
        }

        UniversityManager.listClassRoom.remove(indexDelete);
        System.out.println("Lớp học đã được xóa thành công.");
    }


    public static void assignTeacherToClassroom(Scanner scanner) {
        System.out.println("Nhập vào mã lớp học cần phân công giảng viên: ");
        int classRoomId = Integer.parseInt(scanner.nextLine());

        Optional<ClassRoom> classRoomOpt = UniversityManager.listClassRoom.stream()
                .filter(classRoom -> classRoom.getClassRoomId() == classRoomId)
                .findFirst();

        if (!classRoomOpt.isPresent()) {
            System.err.println("Lớp học không tồn tại với mã: " + classRoomId);
            return;
        }

        ClassRoom classRoom = classRoomOpt.get();

        if (classRoom.getStatus() == StatusClassRoom.CLOSE) {
            System.err.println("Không thể phân công giảng viên cho lớp học vì lớp học đã đóng.");
            return;
        }

        if (classRoom.getTeacherId() == 0) {
            System.err.println("Lớp học này đã có giảng viên.");
            return;
        }

        System.out.println("Nhập vào mã giảng viên cần phân công: ");
        int teacherId = Integer.parseInt(scanner.nextLine());

        Optional<Teacher> teacherOpt = UniversityManager.teacherList.stream()
                .filter(teacher -> teacher.getTeacherId() == teacherId)
                .findFirst();

        if (!teacherOpt.isPresent()) {
            System.err.println("Không tìm thấy giảng viên với mã: " + teacherId);
            return;
        }

        Teacher teacher = teacherOpt.get();

        classRoom.setTeacherId(teacherId);
        System.out.println("Giảng viên " + teacher.getName() + " đã được phân công cho lớp học " + classRoom.getClassRoomName());
    }


    public static void addStudentToClassroom(Scanner scanner) {
        System.out.print("Nhập vào mã lớp học: ");
        int classRoomId = Integer.parseInt(scanner.nextLine());

        Optional<ClassRoom> classRoomOpt = UniversityManager.listClassRoom.stream()
                .filter(c -> c.getClassRoomId() == classRoomId)
                .findFirst();

        if (!classRoomOpt.isPresent()) {
            System.err.println("Không tìm thấy lớp học với mã: " + classRoomId);
            return;
        }

        ClassRoom classRoom = classRoomOpt.get();

        if (classRoom.getStatus() == StatusClassRoom.CLOSE) {
            System.err.println("Không thể thêm sinh viên vì lớp học đã đóng.");
            return;
        }

        System.out.print("Nhập vào mã sinh viên cần thêm: ");
        String studentId = scanner.nextLine();

        Optional<Student> studentOpt = UniversityManager.studentList.stream()
                .filter(s -> s.getStudentId().equalsIgnoreCase(studentId))
                .findFirst();

        if (!studentOpt.isPresent()) {
            System.err.println("Không tìm thấy sinh viên với mã: " + studentId);
            return;
        }

        Student student = studentOpt.get();

        if (classRoom.getListStudents().contains(student)) {
            System.err.println("Sinh viên đã tồn tại trong lớp học.");
            return;
        }

        classRoom.getListStudents().add(student);
        System.out.println("Đã thêm sinh viên " + student.getName() + " vào lớp " + classRoom.getClassRoomName());
    }

    public static void createSchedule(Scanner scanner) {
        if (UniversityManager.listClassRoom.isEmpty()) {
            System.err.println("Không có lớp học nào để tạo lịch.");
            return;
        }

        Schedule schedule = new Schedule();
        schedule.inputData(scanner);

        if (schedule.getClassRoomId() == -1) {
            System.err.println("Không có lớp học nào khả dụng.");
            return;
        }

        UniversityManager.listSchedule.add(schedule);
        System.out.println("Tạo lịch học thành công!");
    }

    public static void updateClassroomStatus(Scanner scanner) {
        System.out.println("Nhập vào mã lớp học cần cập nhật trạng thái: ");
        int classRoomId = Integer.parseInt(scanner.nextLine());

        Optional<ClassRoom> classRoomOpt = UniversityManager.listClassRoom.stream()
                .filter(c -> c.getClassRoomId() == classRoomId)
                .findFirst();

        if (!classRoomOpt.isPresent()) {
            System.err.println("Không tìm thấy lớp học với mã: " + classRoomId);
            return;
        }

        ClassRoom classRoom = classRoomOpt.get();
        StatusClassRoom currentStatus = classRoom.getStatus();

        switch (currentStatus) {
            case PENDING:
                classRoom.setStatus(StatusClassRoom.PROGESS);
                System.out.println("Trạng thái được cập nhật từ PENDING → PROGRESS");
                break;
            case PROGESS:
                classRoom.setStatus(StatusClassRoom.CLOSE);
                System.out.println("Trạng thái được cập nhật từ PROGRESS → CLOSE");
                break;
            case CLOSE:
                System.out.println("Lớp học đã ở trạng thái CLOSE. Không thể cập nhật thêm.");
                break;
        }
    }
}
