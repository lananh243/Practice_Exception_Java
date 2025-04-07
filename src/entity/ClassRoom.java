package entity;

import presentation.UniversityManager;
import util.StringRule;
import util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ClassRoom implements IApp {
    private int idAutocreasement = 0;
    private int classRoomId;
    private String classRoomName;
    private String courseId;
    private int teacherId;
    private List<Student> listStudents;
    private LocalDate created;
    private StatusClassRoom status;

    public ClassRoom() {
        this.classRoomId = ++idAutocreasement;
    }

    public ClassRoom(String classRoomName, String courseId, int teacherId, List<Student> listStudents, LocalDate created, StatusClassRoom status) {
        this.classRoomId = ++idAutocreasement;
        this.classRoomName = classRoomName;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.listStudents = listStudents != null ? listStudents : new ArrayList<>();
        this.created = created;
        this.status = status;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public StatusClassRoom getStatus() {
        return status;
    }

    public void setStatus(StatusClassRoom status) {
        this.status = this.status;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.classRoomName =  Validator.validateInputString(scanner, "Nhập vào tên lớp học: ", new StringRule(15, 50));
        this.courseId = choiceCourseId(scanner);
        this.teacherId = choiceTeacherId(scanner);
        this.created = inputCreatedDate(scanner);
    }

    private String choiceCourseId(Scanner scanner) {
        System.out.println("Chọn mã khóa học: ");
        while (true) {
            try {
                if (UniversityManager.courseList.isEmpty()) {
                    return null;
                }
                UniversityManager.courseList.forEach(course -> System.out.printf("%s", course.getCourseId()));
                System.out.println("Lựa chọn của bạn: ");
                String choiceId = scanner.nextLine().trim();

                OptionalInt indexCourse = IntStream.range(0, UniversityManager.courseList.size())
                        .filter(index -> UniversityManager.courseList.get(index).getCourseId().equals(choiceId)).findFirst();
                if (indexCourse.isPresent()) {
                    return UniversityManager.courseList.get(indexCourse.getAsInt()).getCourseId();
                }
                throw new IllegalArgumentException("Vui lòng chọn đúng mã khóa học");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private int choiceTeacherId(Scanner scanner) {
        System.out.println("Chọn mã giảng viên: ");
        while (true) {
            try {
                if (UniversityManager.teacherList.isEmpty()) {
                    return -1;
                }
                UniversityManager.teacherList.forEach(teacher -> System.out.printf("%d", teacher.getTeacherId()));
                System.out.println("Lựa chọn của bạn: ");
                int choiceId = Integer.parseInt(scanner.nextLine());
                OptionalInt indexTeacher = IntStream.range(0, UniversityManager.teacherList.size())
                        .filter(index -> UniversityManager.teacherList.get(index).getTeacherId() == choiceId).findFirst();

                if (indexTeacher.isPresent()) {
                    return UniversityManager.teacherList.get(indexTeacher.getAsInt()).getTeacherId();
                }
            } catch (NumberFormatException ex) {
                System.err.println("Vui lòng nhập số nguyên hợp lệ");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LocalDate inputCreatedDate(Scanner scanner) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Nhập vào ngày tạo:");
        return LocalDate.parse(scanner.nextLine(), dtf);
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classRoomId=" + classRoomId +
                ", teacherId=" + teacherId +
                ", courseId='" + courseId + '\'' +
                ", classRoomName='" + classRoomName + '\'' +
                ", listStudents=" + listStudents +
                ", created=" + created +
                ", status=" + status +
                '}';
    }
}
