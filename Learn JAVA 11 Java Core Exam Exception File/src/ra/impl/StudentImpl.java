package ra.impl;

import ra.entity.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentImpl {
    public static void displayStudent(List<Student> studentList){
        for (Student student:studentList){
            student.displayData();
        }
    }
    public static void inputStudent(Scanner scanner , List<Student> studentList){
        System.out.println("1. Nhập thông tin các sinh viên");
        System.out.println("Nhập số học sinh cần thêm");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Student student = new Student();
            student.inputData(scanner,studentList);
            studentList.add(student);
        }
    }
    public static void sortByAge(List<Student> studentList){
        System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
        Comparator<Student> StudentAge = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() <= o2.getAge() ? -1 : o1.getAge() == o2.getAge() ? 0 : 1;
            }
        };
        studentList.sort(StudentAge);
    }
    public static void sortByAvgMark(List<Student> studentList){
        System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
        Comparator<Student> StudentAvgMark = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Float.compare(o2.getAvgMark(), o1.getAvgMark());
            }
        };
        studentList.sort(StudentAvgMark);

    }
    public static void calAGEEE(List<Student> studentList){
        for (Student student : studentList){
            student.calAge();
        }
    }
    public static void calgetAVGandRank(List<Student> studentList){
        for (Student student: studentList){
            student.calAvgMark_Rank();
        }
    }
    public static void updateStudent(Scanner scanner,List<Student> studentList) {
        System.out.println("Nhập mã sinh viên cần cập nhật");
        String studentId = scanner.nextLine();
        Student student = new Student();
        boolean isExit = true;
        do {
            int indexUpdate = getIndexOfListStudent(studentId,  studentList);
            if (indexUpdate >= 0) {
                student.inputData(scanner,studentList);
                System.out.println("Đã cập nhật thông tin sản phẩm.");
                isExit = false;
            }else {
                System.out.println("Không tìm thấy mã sinh viên");
            }
        }while (isExit);

    }
    public static void searchStudentByName(Scanner scanner,List<Student> studentList){
        System.out.println(" 7. Tìm kiếm sinh viên theo tên sinh viên");
        System.out.println("Nhập tên cần tìm kiểm");
        String searchName = scanner.nextLine();
        for (Student student: studentList){
            if (student.getStudentName().equals(searchName)){
                student.displayData();
            }
        }
    }
    public static int getIndexOfListStudent(String studentId, List<Student> studentList) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }


}
//1. Nhập thông tin các sinh viên
//        2. Tính tuổi các sinh viên
//        3. Tính điểm trung bình và xếp loại sinh viên
//        4. Sắp xếp sinh viên theo tuổi tăng dần
//        5. Thống kê sinh viên theo xếp loại sinh viên
//        6. Cập nhật thông tin sinh viên theo mã sinh viên
//        7. Tìm kiếm sinh viên theo tên sinh viên
