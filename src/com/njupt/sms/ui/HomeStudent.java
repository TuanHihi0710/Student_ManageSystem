package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.ChooseCourse;
import com.njupt.sms.beans.Student;
import com.njupt.sms.utils.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HomeStudent {
    private JPanel homeStudent;
    private JButton exitButton;
    private JTabbedPane tabbedPane1;
    private JTextField studentCode;
    private JTextField name;
    private JTextField age;
    private JTextField sex;
    private JTextField birthday;
    private JTextField address;
    private JTextField phone;
    private JTextField email;
    private JButton modifyButton;
    private JLabel nameLabel;
    private JTextField studentClass;
    private JTable table1;
    private JButton chooseOkButton;
    private JTable table2;
    private JFrame frame;

    private GradesQueryModel gradesQueryModel;

    private ChooseCourseModel chooseCourseModel;


    public static void main(String[] args) {

    }

    public HomeStudent() {
        StudentInfoUtils studentInfoUtils = new StudentInfoUtils();
        Student sessionStudent = (Student) Session.userInfo;
        Student databaseStudent = studentInfoUtils.findStudentInfoByUsername(sessionStudent.getUsername());
        updateStudentInfo(databaseStudent);

        chooseCourseModel = new ChooseCourseModel();
        table1.setModel(chooseCourseModel);


        gradesQueryModel = new GradesQueryModel();
        table2.setModel(gradesQueryModel);

        frame = new JFrame("HomeStudent");
        frame.setContentPane(homeStudent);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();

        UICommonUtils.makeFrameToCenter(frame);

        frame.setVisible(true);


        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student preModifyStudent = new Student();

                preModifyStudent.setAge(Integer.parseInt(age.getText().trim()));
                preModifyStudent.setSex(sex.getText().trim());

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date newDate = null;
                try {
                    newDate = dateFormat.parse(birthday.getText().trim());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                preModifyStudent.setBirthday(newDate);
                preModifyStudent.setAddress(address.getText().trim());
                preModifyStudent.setPhone(phone.getText().trim());
                preModifyStudent.setEmail(email.getText().trim());
                preModifyStudent.setStudentCode(studentCode.getText().trim());
                preModifyStudent.setStudentClass(Integer.parseInt(studentClass.getText().trim()));

                boolean flag = studentInfoUtils.saveStudentInfo(preModifyStudent);
                if (flag) {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thành công", "Thông tin", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(frame, "Cập nhật thất bại", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
                LoginLogoutUtils loginLogoutUtils = new LoginLogoutUtils();
                loginLogoutUtils.logout();
            }
        });

        chooseOkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() > -1) {
                    int courseId = Integer.parseInt(chooseCourseModel.getValueAt(table1.getSelectedRow(), 0).toString().trim());


                    System.out.println(chooseCourseModel.getValueAt(table1.getSelectedRow(), 5));
                    if (chooseCourseModel.getValueAt(table1.getSelectedRow(), 5) != "") {
                        chooseCourseModel.updateChoosenInfo();
                        return;
                    }
                    boolean flag = chooseCourseModel.saveChooseCourse(courseId);
                    if (flag) {
                        System.out.println("Đã lưu thành công thông tin lựa chọn khóa học");

                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng chọn một cột trong bảng trên", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }


    private void updateStudentInfo(Student student) {
        studentCode.setText(student.getStudentCode().trim());
        name.setText(student.getName().trim());
        age.setText(student.getAge() + "");
        sex.setText(student.getSex());
        birthday.setText(student.getBirthday().toString());
        address.setText(student.getAddress());
        phone.setText(student.getPhone());
        email.setText(student.getEmail());
        nameLabel.setText(student.getName().trim());
        studentClass.setText(student.getStudentClass() + "");

    }

    private class ChooseCourseModel extends AbstractTableModel {

        Student student = (Student) Session.userInfo;


        private ChooseCourseUtils chooseCourseUtils = new ChooseCourseUtils();
        private List<Map<String, Object>> list = getALlChooseCourse();


        String[] columnStrings = {"id", "courseName", "academicYear", "term", "name", "hasChoosen", "teacherId"};
        String[] columnShowStrings = {"ID", "Tên khóa học", "Năm học", "Học kỳ", "Giáo viên phụ trách", "Trạng thái"};

        public void updateChoosenInfo() {
            list = getALlChooseCourse();
            fireTableDataChanged();

        }

        public boolean saveChooseCourse(int courseId) {
            boolean flag = chooseCourseUtils.saveChoosen(student.getId(), courseId);
            updateChoosenInfo();

            return flag;
        }

        public List<Map<String, Object>> getALlChooseCourse() {
            return chooseCourseUtils.findAllChooseCourse(student.getId());
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnStrings.length - 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }
    }


    private class GradesQueryModel extends AbstractTableModel {

        Student student = (Student) Session.userInfo;

        private GradeUtils gradeUtils = new GradeUtils();
        private List<Map<String, Object>> list = gradeUtils.getGradesByStudentId(student.getId());

        String[] columnStrings = {"id", "courseName", "academicYear", "term", "name", "score"};
        String[] columnShowingStrings = {"ID", "Tên khóa học", "Năm học", "Học kỳ", "Giáo viên phụ trách", "Điểm số"};


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowingStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public String getColumnName(int column) {
            return columnShowingStrings[column];
        }
    }
}
