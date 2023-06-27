package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Admin;
import com.njupt.sms.beans.Course;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;
import com.njupt.sms.utils.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.sql.Savepoint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class HomeAdmin {
    private JPanel homeAdmin;
    private JButton exitButton;
    private JLabel nameLabel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTable table2;
    private JButton addOneRowCourseButton;
    private JButton deleteOneRowCourseButton;
    private JButton addOneRowTeacherButton1;
    private JButton deleteOneRowTeacherButton1;
    private JButton saveCourseButton;
    private JButton saveTeacherButton;
    private JTable table3;
    private JButton addOneStudentRowButton;
    private JButton saveStudentTableButton;
    private JButton deleteOneStudentRowButton;
    private JTable table4;
    private JButton cleanButton;
    private JTable table5;
    private JTable table6;
    private JButton statisticButton;
    private JTabbedPane tabbedPane2;
    private JTable course;
    private JTable studentClass;
    private JTable student;
    private JTable time;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JFrame frame;

    private CourseModel courseModel;
    private TeacherModel teacherModel;

    private StudentInfoModel studentInfoModel;

    private ImportedGradeModel importedGradeModel;

    private CourseInfoModel courseInfoModel;
    private GradeInputModel gradeInputModel;

    private CourseStatisticModel courseStatisticModel;
    private TimeStatisticModel timeStatisticModel;
    private ClassStatisticModel classStatisticModel;
    private StudentStatisticModel studentStatisticModel;

    public static void main(String[] args) {

    }

    public HomeAdmin() {
        courseModel = new CourseModel();
        teacherModel = new TeacherModel();

        table1.setModel(courseModel);
        table2.setModel(teacherModel);

        studentInfoModel = new StudentInfoModel();
        table3.setModel(studentInfoModel);

        importedGradeModel = new ImportedGradeModel();
        table4.setModel(importedGradeModel);

        courseInfoModel = new CourseInfoModel();
        table5.setModel(courseInfoModel);

        gradeInputModel = new GradeInputModel();
        table6.setModel(gradeInputModel);

        courseStatisticModel = new CourseStatisticModel();
        timeStatisticModel = new TimeStatisticModel();
        classStatisticModel = new ClassStatisticModel();
        studentStatisticModel = new StudentStatisticModel();

//        private JTable course;
//        private JTable studentClass;
//        private JTable student;
//        private JTable time;
        course.setModel(courseStatisticModel);
        studentClass.setModel(classStatisticModel);
        time.setModel(timeStatisticModel);
        student.setModel(studentStatisticModel);


        Admin admin = (Admin) Session.userInfo;
        nameLabel.setText(admin.getUsername());


        frame = new JFrame("HomeAdmin");
        frame.setContentPane(homeAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();

        UICommonUtils.makeFrameToCenter(frame);

        frame.setVisible(true);

        addOneRowCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(table2.getSelectedRow() > -1) || table2.getValueAt(table2.getSelectedRow(), 3) == null) {
                    JOptionPane.showMessageDialog(frame, "Hãy chọn một hàng có tên giáo viên trong bảng giáo viên", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Object id = teacherModel.getValueAt(table2.getSelectedRow(), 0);
                    Object name = teacherModel.getValueAt(table2.getSelectedRow(), 3);
                    map.put("teacherId", id);
                    map.put("name", name);

                    courseModel.addRow(map);
                }

            }
        });


        addOneRowTeacherButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Object> map = new HashMap<String, Object>();
                teacherModel.addRow(map);

            }
        });
        saveTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherModel.save();

            }
        });

        saveCourseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                courseModel.save();

            }
        });
        deleteOneRowCourseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() > -1) {
                    courseModel.remove(table1.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng chọn một bản ghi", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        deleteOneRowTeacherButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ((table2.getSelectedRow() > -1)) {
                    teacherModel.remove(table2.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng chọn một bản ghi", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addOneStudentRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Object> map = new HashMap<String, Object>();
                studentInfoModel.addRow(map);
            }
        });


        deleteOneStudentRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table3.getSelectedRow() > -1) {
                    studentInfoModel.removeStudentWithIndex(table3.getSelectedRow());

                } else {
                    JOptionPane.showMessageDialog(frame, "Vui lòng chọn một bản ghi", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        saveStudentTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentInfoModel.saveStudentTable();

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
                Session.userInfo = null;
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table4.getSelectedRow() < 0) {
                    return;
                }
                int courseId = Integer.parseInt(importedGradeModel.getValueAt(table4.getSelectedRow(), 0).toString().trim());
                importedGradeModel.cleanCommitStatusByCourseId(courseId);

                courseInfoModel.update();
            }
        });
        table5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table5.getSelectedRow() < 0) {
                    return;
                }
                int courseId = Integer.parseInt(courseInfoModel.getValueAt(table5.getSelectedRow(), 0).toString().trim());
                gradeInputModel.setStudentByCourseId(courseId, false);
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                private CourseStatisticModel courseStatisticModel;
//                private TimeStatisticModel timeStatisticModel;
//                private ClassStatisticModel classStatisticModel;
//                private StudentStatisticModel studentStatisticModel;

                courseStatisticModel.update();
                timeStatisticModel.update();
                classStatisticModel.update();
                studentStatisticModel.update();

            }
        });


        statisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tabbedPane2.getSelectedIndex();
                if (index == 0) {
                    if (course.getSelectedRow() < 0) {
                        return;
                    }
                    String courseName = courseStatisticModel.getValueAt(course.getSelectedRow(), 0).toString();
                    Map<String, Object> map = courseStatisticModel.getData(courseName);
                    makeChartByMap(map, "Biểu đồ thống kê điểm số cho khóa học " + courseName);

                } else if (index == 1) {
                    if (studentClass.getSelectedRow() < 0) {
                        return;
                    }
                    int classCode = Integer.parseInt(classStatisticModel.getValueAt(studentClass.getSelectedRow(), 0).toString());
                    Map<String, Object> map = classStatisticModel.getData(classCode);
                    makeChartByMap(map, "Biểu đồ thống kê điểm số cho lớp " + classCode);

                } else if (index == 2) {
                    if (student.getSelectedRow() < 0) {
                        return;
                    }
                    String studentCode = studentStatisticModel.getValueAt(student.getSelectedRow(), 0).toString();
                    Map<String, Object> map = studentStatisticModel.getData(studentCode);
                    makeChartByMap(map, "Biểu đồ thống kê điểm số cho học sinh có mã " + studentCode);
                } else if (index == 3) {
                    if (time.getSelectedRow() < 0) {
                        return;
                    }
                    String academicQuery = timeStatisticModel.getValueAt(time.getSelectedRow(), 0).toString();
                    String termQuery = timeStatisticModel.getValueAt(time.getSelectedRow(), 1).toString();
                    Map<String, Object> map = timeStatisticModel.getData(academicQuery, termQuery);
                    makeChartByMap(map, "Biểu đồ thống kê điểm số cho năm học " + academicQuery + " - học kỳ " + termQuery);
                }

            }
        });

    }

    public void makeChartByMap(Map<String, Object> map, String title) {
        DefaultPieDataset dpd = new DefaultPieDataset();
        System.out.println(map);

        dpd.setValue("Xuất sắc", Integer.parseInt(map.get("Xuất sắc").toString()));
        dpd.setValue("Giỏi", Integer.parseInt(map.get("Giỏi").toString()));
        dpd.setValue("Khá", Integer.parseInt(map.get("Khá").toString()));
        dpd.setValue("Trung bình", Integer.parseInt(map.get("Trung bình").toString()));
        dpd.setValue("Dưới trung bình", Integer.parseInt(map.get("Dưới trung bình").toString()));

        JFreeChart chart = ChartFactory.createPieChart(title, dpd, true, true, false);
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}:({2})"), NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
        ChartFrame chartFrame = new ChartFrame(title, chart);
        chartFrame.pack();
        chartFrame.setVisible(true);
    }


    private class TeacherModel extends AbstractTableModel {

        TeacherUtils teacherUtils = new TeacherUtils();
        List<Map<String, Object>> list = teacherUtils.findAllTeachers();

        String[] tableStrings = {"id", "username", "password", "name", "phone", "email"};
        String[] showStrings = {"ID", "Tên đăng nhập", "Mật khẩu", "Họ và tên", "Số điện thoại", "Địa chỉ mail"};


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return tableStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(tableStrings[columnIndex]);
        }


        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        public void addRow(Map<String, Object> row) {
            list.add(row);
            fireTableDataChanged();
        }

        public void save() {
            for (int i = 0; i < list.size(); i++) {
                boolean flag = teacherUtils.saveTeacher(list.get(i));
            }
            list = teacherUtils.findAllTeachers();
            fireTableDataChanged();

        }

        public void remove(int rowIndex) {
            Map<String, Object> map = list.get(rowIndex);
            if (map.containsKey("id")) {
                teacherUtils.removeTeacher(Integer.parseInt(map.get("id").toString()));
            }
            list.remove(rowIndex);
            fireTableDataChanged();

        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }


    }


    private class CourseModel extends AbstractTableModel {

        CourseUtils courseUtils = new CourseUtils();
        List<Map<String, Object>> list = courseUtils.findAllCourse();

        String[] tableStrings = {"id", "courseName", "academicYear", "term", "name", "teacherId"};
        String[] showStrings = {"ID", "Khóa học", "Năm học", "Học kỳ", "Giáo viên phụ trách"};

        public void addRow(Map<String, Object> row) {
            list.add(row);
            fireTableDataChanged();
        }


        public void save() {

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                courseUtils.saveCourse(map);
            }
            list = courseUtils.findAllCourse();
            fireTableDataChanged();

        }


        public void remove(int rowIndex) {
            Map<String, Object> map = list.get(rowIndex);
            if (map.containsKey("id")) {
                courseUtils.removeCourse(Integer.parseInt(map.get("id").toString()));
            }
            list.remove(rowIndex);
            fireTableDataChanged();

        }


        @Override
        public int getRowCount() {

            return list.size();
        }

        @Override
        public int getColumnCount() {
            return tableStrings.length - 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(tableStrings[columnIndex]);

        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }

        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

    }

    private class StudentInfoModel extends AbstractTableModel {

        private StudentInfoUtils studentInfoUtils = new StudentInfoUtils();
        private List<Map<String, Object>> list = getAllStudentsInfo();

        public List<Map<String, Object>> getAllStudentsInfo() {
            return studentInfoUtils.getAllStudentsInfo();
        }

        String[] columnStrings = {"id", "username", "password", "studentCode", "name", "studentClass", "age", "sex", "birthday", "address", "phone", "email"};
        String[] columnShowStrings = {"ID", "Tên đăng nhập", "Mật khẩu", "Mã học sinh", "Họ và tên", "Lớp", "Tuổi", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Địa chỉ mail"};

        public void addRow(Map<String, Object> map) {
            list.add(map);
            fireTableDataChanged();

        }

        public void saveStudentTable() {

            for (int i = 0; i < list.size(); i++) {
                studentInfoUtils.saveStudentInfoByMap(list.get(i));
            }

            list = getAllStudentsInfo();
            fireTableDataChanged();

        }

        public void removeStudentWithIndex(int index) {
            Map<String, Object> map = list.get(index);
            if (map.containsKey("id")) {
                boolean flag = studentInfoUtils.deleteStudentById(Integer.parseInt(map.get("id").toString().trim()));
                if (flag == true) {
                    list.remove(index);
                }
            } else {
                list.remove(index);
            }
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(columnStrings[columnIndex], aValue);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }

    }

    private class ImportedGradeModel extends AbstractTableModel {

        private CourseUtils courseUtils = new CourseUtils();
        private List<Map<String, Object>> list = getAllCourseInfo();

        String[] columnStrings = {"id", "courseName", "academicYear", "term", "name", "commitStatus"};
        String[] columnShowingStrings = {"ID", "Tên khóa học", "Năm học", "Học kỳ", "Giáo viên phụ trách", "Trạng thái"};

        public void updateTable() {
            list = getAllCourseInfo();
        }

        public void cleanCommitStatusByCourseId(int id) {
            courseUtils.clearCommitStautsByCourseId(id);

            updateTable();
            fireTableDataChanged();

        }

        private List<Map<String, Object>> getAllCourseInfo() {
            return courseUtils.findAllCourse();
        }

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

    private class CourseInfoModel extends AbstractTableModel {


        private CourseUtils courseUtils = new CourseUtils();
        private List<Map<String, Object>> list = getAllCourses();


        String[] columnStrings = {"id", "courseName", "academicYear", "term", "commitStatus"};
        String[] columnShowStrings = {"ID", "Tên khóa học", "Năm học", "Học kỳ", "Trạng thái"};


        public void update() {
            list = getAllCourses();
            fireTableDataChanged();

        }

        private List<Map<String, Object>> getAllCourses() {
            return courseUtils.findAllCourse();
        }


        @Override
        public int getRowCount() {
            //System.out.println(list);
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnStrings.length;
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

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {


            return false;
        }

    }

    private class GradeInputModel extends AbstractTableModel {

        private int courseId;


        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        private CourseUtils courseUtils = new CourseUtils();
        private GradeUtils gradeUtils = new GradeUtils();
        private List<Map<String, Object>> list = new ArrayList<>();


        String[] columnStrings = {"id", "studentCode", "name", "score", "courseId"};
        String[] columnShowStrings = {"ID", "Mã học sinh", "Họ và tên", "Điểm số"};

        public List<Map<String, Object>> getAllStudentByCourseId(int courseId, boolean useDraft) {
            if (useDraft == true) {
                return courseUtils.findAllStudentWithGradeDraftByCourseId(courseId);
            } else {
                return courseUtils.findAllStudentWithGradeByCourseId(courseId);
            }

        }

        public boolean commitGrades() {
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                if (map.get("score") == null || map.get("score") == "") {
                    JOptionPane.showMessageDialog(frame, "Vui lòng hoàn thành kết quả trước khi gửi", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                gradeUtils.saveGrade(map);
                courseUtils.commitCourseByCourseId(courseId);

            }
            return true;


        }

        public void setStudentByCourseId(int courseId, boolean useDraft) {

            this.courseId = courseId;

            list = getAllStudentByCourseId(courseId, useDraft);
            fireTableDataChanged();

        }

        public void saveToDraft() {
            for (int i = 0; i < list.size(); i++) {
                gradeUtils.saveGradeToDraft(list.get(i));
            }

            courseUtils.draftCourseByCourseId(courseId);
            setStudentByCourseId(courseId, true);


        }


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
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

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == columnShowStrings.length - 1;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(columnStrings[columnIndex], aValue);

        }


    }

    private class CourseStatisticModel extends AbstractTableModel {
        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllCourses();
        String[] columnStrings = {"courseName"};
        String[] columnShowStrings = {"Tên khóa học"};


        private Map<String, Object> getData(String courseName) {

            return statisticUtils.getCourseStatisticalByCourseName(courseName).get(0);
        }

        public void update() {
            list = statisticUtils.getAllCourses();
            fireTableDataChanged();

        }

        @Override

        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
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

    private class StudentStatisticModel extends AbstractTableModel {
        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllStudents();
        String[] columnStrings = {"studentCode", "name"};
        String[] columnShowStrings = {"Mã học sinh", "Họ và tên"};


        private Map<String, Object> getData(String studentCode) {

            return statisticUtils.getStudentStatisticalByStudentCode(studentCode).get(0);
        }

        public void update() {
            list = statisticUtils.getAllStudents();
            fireTableDataChanged();

        }

        @Override

        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
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

    private class ClassStatisticModel extends AbstractTableModel {
        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllClasses();
        String[] columnStrings = {"studentClass"};
        String[] columnShowStrings = {"Lớp"};


        private Map<String, Object> getData(int studentClass) {

            return statisticUtils.getClassStatisticalByClass(studentClass).get(0);
        }

        public void update() {
            list = statisticUtils.getAllClasses();
            fireTableDataChanged();

        }

        @Override

        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
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


    private class TimeStatisticModel extends AbstractTableModel {
        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllTimes();
        String[] columnStrings = {"academicYear", "term"};
        String[] columnShowStrings = {"Năm học", "Học kỳ"};

        private Map<String, Object> getData(String academicYear, String term) {

            return statisticUtils.getTimeStatisticalTime(academicYear, term).get(0);
        }

        public void update() {
            list = statisticUtils.getAllTimes();
            fireTableDataChanged();

        }

        @Override

        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
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
}

