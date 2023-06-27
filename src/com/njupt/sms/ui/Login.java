package com.njupt.sms.ui;

import com.njupt.sms.beans.Admin;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;
import com.njupt.sms.utils.LoginLogoutUtils;
import com.njupt.sms.utils.UICommonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JRadioButton studentRadioButton;
    private JRadioButton teacherRadioButton;
    private JRadioButton adminRadioButton;
    private JButton LoginButton;
    private JPanel LoginPanel;
    private static JFrame frame;

    public Login() {
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernameTextField.getText() == null || usernameTextField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập tên tài khoản", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (passwordTextField.getText() == null || passwordTextField.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(frame, "xin vui lòng nhập mật khẩu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }


                LoginLogoutUtils loginLogoutUtils = new LoginLogoutUtils();
                String username = usernameTextField.getText().trim();
                String password = passwordTextField.getText().trim();
                Object userinfo = null;

                if (studentRadioButton.isSelected()) {
                    userinfo = new Student(username, password);
                } else if (adminRadioButton.isSelected()) {
                    userinfo = new Admin(username, password);
                } else {
                    userinfo = new Teacher(username, password);
                }

                String result = loginLogoutUtils.login(userinfo);
                // System.out.println(userinfo);

                if (!result.equals("Đăng nhập thành công")) {
                    JOptionPane.showMessageDialog(frame, result, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (studentRadioButton.isSelected()) {
                        new HomeStudent();
                    } else if (adminRadioButton.isSelected()) {
                        new HomeAdmin();
                    } else {
                        new HomeTeacher();
                    }
                    frame.dispose();
                }
            }
        });


        frame = new JFrame("Đăng nhập");
        frame.setContentPane(LoginPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        UICommonUtils.makeFrameToCenter(frame);
        frame.setSize(500, 250);

        frame.setVisible(true);


        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    passwordTextField.requestFocus();

                }
                super.keyPressed(e);
            }
        });
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    LoginButton.doClick();

                }
                super.keyPressed(e);
            }
        });
    }
}
