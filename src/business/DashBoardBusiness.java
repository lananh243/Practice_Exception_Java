package business;

import entity.ClassRoom;
import entity.Course;
import presentation.UniversityManager;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DashBoardBusiness {
    public static void showStatistic() {
        System.out.println("Số lượng sinh viên: "+ UniversityManager.studentList.size());
        System.out.println("Số lượng giảng viên: "+ UniversityManager.teacherList.size());
        System.out.println("Số lượng khóa học: "+UniversityManager.courseList.size());
        System.out.println("Số lượng lớp học: "+UniversityManager.listClassRoom.size());
    }

    public static void top3CoursesMostStudents() {
        Map<String, Integer> courseStudentCounts = UniversityManager.listClassRoom.stream()
                .filter(classRoom -> classRoom.getListStudents() != null)
                .collect(Collectors.toMap(
                        ClassRoom::getCourseId,
                        classRoom -> classRoom.getListStudents().size(),
                        Integer::sum
                ));

        List<Map.Entry<String, Integer>> top3 = courseStudentCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 khóa học có nhiều sinh viên nhất:");
        top3.forEach(entry -> {
            System.out.println("Mã khóa học: " + entry.getKey() + " - Tổng số sinh viên: " + entry.getValue());
        });
    }

    public static void top3ClassRoomMostStudents(){
        UniversityManager.listClassRoom.stream()
                .filter(classRoom -> classRoom.getListStudents() != null)
                .sorted((c1, c2) -> c2.getListStudents().size() - c1.getListStudents().size())
                .limit(3)
                .forEach(classRoom -> {
                    System.out.println("Lớp học: "+classRoom.getClassRoomName()+ " - Số sinh viên: " + classRoom.getListStudents().size());
                });
    }

    public static void top3TeacherMostStudents(){
        Map<Integer, Integer> teacherStudentCount = UniversityManager.listClassRoom.stream()
                .collect(Collectors.groupingBy(
                        ClassRoom::getTeacherId,
                        Collectors.summingInt(c -> c.getListStudents().size())
                ));

        List<Map.Entry<Integer, Integer>> top3Teachers = teacherStudentCount.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 giảng viên dạy nhiều sinh viên nhất:");
        top3Teachers.forEach(entry -> {
            System.out.println("Teacher ID: " + entry.getKey() + ", Số sinh viên: " + entry.getValue());
        });
    }


    public static void top3CourseRegisterMostStudents() {
        Map<String, Double> studentClassCount = UniversityManager.listClassRoom.stream()
                .flatMap(classRoom -> classRoom.getListStudents().stream())
                .collect(Collectors.groupingBy(
                        student -> student.getStudentId(),
                        Collectors.summingDouble(student -> 1)
                ));
        List<Map.Entry<String, Double>> top3Students = studentClassCount.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("Top 3 sinh viên đăng ký học nhiều nhất:");
        top3Students.forEach(entry -> {
            System.out.println("Student ID: " + entry.getKey() + ", Số lớp học đã đăng ký: " + entry.getValue());
        });
    }
}
