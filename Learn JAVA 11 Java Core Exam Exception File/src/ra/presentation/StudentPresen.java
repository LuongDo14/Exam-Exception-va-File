package ra.presentation;

import ra.entity.Student;
import ra.impl.StudentImpl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentPresen extends Student{
    public static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*****************MENU*******************");
            System.out.println("1. Nhập thông tin các sinh viên");
            System.out.println("2. Tính tuổi các sinh viên");
            System.out.println("3. Tính điểm trung bình và xếp loại sinh viên");
            System.out.println("4. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("5. Thống kê sinh viên theo xếp loại sinh viên");
            System.out.println("6. Cập nhật thông tin sinh viên theo mã sinh viên");
            System.out.println("7. Tìm kiếm sinh viên theo tên sinh viên");
            System.out.println("8. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    StudentImpl.inputStudent(scanner,studentList);
                    break;
                case 2:
                    StudentImpl.calAGEEE(studentList);
                    break;
                case 3:
                    StudentImpl.calgetAVGandRank(studentList);
                    break;
                case 4:
                    StudentImpl.sortByAge(studentList);
                    break;
                case 5:
                    StudentImpl.sortByAvgMark(studentList);
                    break;
                case 6:
                    StudentImpl.updateStudent(scanner,studentList);
                    break;
                case 7:
                    StudentImpl.searchStudentByName(scanner,studentList);
                    break;
                case 8:
                    System.exit(0);
                case 9:
                    StudentImpl.displayStudent(studentList);
                    break;
                case 10:
                    writeDataToFile(studentList);
                    break;
                case 11:
                    readDataFromFile();
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        }while (true);
    }
    public static void writeDataToFile(List<Student> studentList) {
        //1. Khởi tạo file

        File file = new File("studentsList.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            //2. Khởi tạo đối tượng FileOutputStream
            fos = new FileOutputStream(file);
            //3. Khởi tạo đối tượng ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            //4. Ghi dữ liệu ra file với phương thức writeObject
            oos.writeObject(studentList);
            //5. Đẩy dữ liệu từ stream xuống file
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readDataFromFile() {
        //1. Khởi tạo đối tượng file
        File file = new File("studentsList.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            //2. Khởi tạo đôi tượng FileInputStream
            fis = new FileInputStream(file);
            //3. Khởi tạo đối tượng ObjectInputStream để đọc object
            ois = new ObjectInputStream(fis);
            //4. Đọc dữ liệu với phương thức readObject()
           studentList = (List<Student>) ois.readObject();
            //In ra kết quả
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ois.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
