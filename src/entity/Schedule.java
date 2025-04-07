package entity;

import presentation.UniversityManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Schedule implements IApp {
    private static int idAutocreasement = 0;
    private int scheduleId;
    private int classRoomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Schedule() {
        this.scheduleId = ++idAutocreasement;
    }

    public Schedule(int classRoomId, LocalDateTime startTime, LocalDateTime endTime) {
        this.scheduleId = ++idAutocreasement;
        this.classRoomId = classRoomId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getClassRoomId() {
        return classRoomId;
    }

    public void setClassRoomId(int classRoomId) {
        this.classRoomId = classRoomId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.classRoomId = choiceStudentId(scanner);
        this.startTime = inputStartTime(scanner);
        this.endTime = inputEndTime(scanner, startTime);
    }

    private int choiceStudentId(Scanner scanner) {
        System.out.println("Chọn mã lớp học: ");
        while (true) {
            try {
                if (UniversityManager.listClassRoom.isEmpty()) {
                    return -1;
                }
                UniversityManager.listClassRoom.forEach(classroom -> System.out.printf("%s", classroom.getClassRoomId()));
                System.out.println("Lựa chọn của bạn: ");
                int choiceId = Integer.parseInt(scanner.nextLine());
                OptionalInt indexStudent = IntStream.range(0, UniversityManager.listClassRoom.size())
                        .filter(index -> UniversityManager.listClassRoom.get(index).getClassRoomId() == choiceId).findFirst();

                if (indexStudent.isPresent()) {
                    return UniversityManager.listClassRoom.get(indexStudent.getAsInt()).getClassRoomId();
                }
                throw new IllegalArgumentException("Vui lòng chọn đúng mã lớp học");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LocalDateTime inputStartTime(Scanner scanner) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Nhập vào thời gian bắt đầu:");
        return LocalDateTime.parse(scanner.nextLine(), dtf);
    }

    private LocalDateTime inputEndTime(Scanner scanner, LocalDateTime startTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Nhập vào thời gian kết thúc :");
        while (true) {
            try {
                String input = scanner.nextLine();
                LocalDateTime endTime = LocalDateTime.parse(input, dtf);

                if (endTime.isAfter(startTime)) {
                    return endTime;
                } else {
                    System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu. Vui lòng nhập lại.");
                }
            } catch (Exception e) {
                System.out.println("Định dạng không hợp lệ. Vui lòng nhập lại");
            }
        }
    }

}
