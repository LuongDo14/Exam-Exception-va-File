package ra.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Student implements IEntity, Serializable {
    private String studentId;
    private String studentName;
    private Date birthday;
    private int age;
    private Boolean sex;
    private float mark_html;
    private float mark_css;
    private float mark_javascript;
    private float avgMark;
    private String rank;

    public Student() {
    }

    public Student(String studentId, String studentName, Date birthday, int age, Boolean sex, float mark_html, float mark_css, float mark_javascript, float avgMark, String rank) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthday = birthday;
        this.age = age;
        this.sex = sex;
        this.mark_html = mark_html;
        this.mark_css = mark_css;
        this.mark_javascript = mark_javascript;
        this.avgMark = avgMark;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public float getMark_html() {
        return mark_html;
    }

    public void setMark_html(float mark_html) {
        this.mark_html = mark_html;
    }

    public float getMark_css() {
        return mark_css;
    }

    public void setMark_css(float mark_css) {
        this.mark_css = mark_css;
    }

    public float getMark_javascript() {
        return mark_javascript;
    }

    public void setMark_javascript(float mark_javascript) {
        this.mark_javascript = mark_javascript;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    @Override
    public <T> void inputData(Scanner scanner, List<T> listStudent) {
        System.out.println("Nhập Id");
        boolean isExit = true;
        do {
            this.studentId = scanner.nextLine();
            if (this.studentId.length() == 4){
                if (this.studentId.startsWith("S")){
                    boolean isExist = false;
                    for (T student : listStudent){
                        if (student instanceof Student){ //kiem tra bien student co phai tu class student hay ko
                            if (((Student) student).getStudentId().equals(this.studentId)){
                                isExist = true;
                                break;
                            }
                        }

                    }
                    if (isExist){
                        System.out.println("Mã sinh viên đã tồn tại");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Mã sinh viên phải bắt đầu bằng S");
                }
                }else {
                System.out.println("Chỉ được dùng 4 ký tự");
            }
        }while (isExit);
        System.out.println("Nhập tên sinh viên");
        do {
            this.studentName = scanner.nextLine();
            if (this.studentName.length() >=10 && this.studentName.length() <=50){
                break;
            }else {
                System.out.println("Tên sinh viên phải có từ 10-50 ký tự");
            }
        }while (isExit);
        System.out.println("Nhập năm sinh dd/MM/yyyy ");
        do {
            String birthStr = scanner.nextLine();
            if (!birthStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    this.birthday = sdf.parse(birthStr);
                    break;
                } catch (ParseException e) {
                    System.err.println("Ngày sinh sinh viên phải có định dạng dd/MM/yyyy, vui lòng nhập lại");
                }
            } else {
                System.err.println("Ngày sinh sinh viên không được để trống, vui lòng nhập lại");
            }
        } while (isExit);
        System.out.println("Nhập giới tính");
        this.sex = Boolean.parseBoolean(scanner.nextLine());
        System.out.println("Nhập điểm html");
        this.mark_html = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập điểm css");
        this.mark_css = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập điểm javascript");
        this.mark_javascript = Float.parseFloat(scanner.nextLine());
    }

    @Override
    public void displayData() {
        System.out.printf("Mã sinh viên : %s - Tên sinh viên : %s - Năm sinh : %s - Tuổi : %d - Giới tính : %s\n"
                ,this.studentId,this.studentName,this.birthday,this.age,(this.sex ? "Nam" : "Nữ"));
        System.out.printf("Điểm html : %f - Điểm css : %f - Điểm javascript : %f \n" , this.mark_html,this.mark_css,this.mark_javascript);
        System.out.printf("Điểm trung bình : %f----- Rank : %s \n" ,this.avgMark,this.rank);
    }

    @Override
    public void calAge() {
        Calendar calendarBirthday = Calendar.getInstance();
        calendarBirthday.setTime(this.birthday);
        float birthYear = calendarBirthday.get(Calendar.YEAR);
        if (birthYear < 2005){
            this.age = (int) (2023 - birthYear);
        }else {
            System.out.println("Năm sinh phải nhỏ hơn 2005");
        }


    }


    @Override
    public void calAvgMark_Rank() {
        this.avgMark = (this.mark_css + this.mark_html + this.mark_javascript) / 3;
        if (avgMark < 5){
            this.rank = "Yếu";
        }else if (avgMark < 7){
            this.rank = "Trung bình";
        }else if (avgMark < 8){
            this.rank = "Khá";
        }else if (avgMark < 9){
            this.rank = "Giỏi";
        }else if (9 < avgMark ){
            this.rank = "Xuất sắc";
        }else {
            System.out.println("Tính không ra kết quả");
        }
    }

//    public static boolean checkEmpty(String str) {
//        if (str == "" || str.trim().length() == 0) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkInteger(String str) {
//        try {
//            Integer.parseInt(str);
//        } catch (NumberFormatException nfe) {
//            return false;
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkFloat(String str) {
//        try {
//            Float.parseFloat(str);
//        } catch (NumberFormatException nfe) {
//            return false;
//        } catch (Exception ex) {
//            return false;
//        }
//        return true;
//    }

}
