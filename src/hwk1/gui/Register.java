package hwk1.gui;

import hwk1.gui.login.Login;
import hwk1.tools.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;

import static hwk1.tools.WindowCenter.initFrame;

public class Register {
    private final JFrame frame = new JFrame("register");
    String registerUserName;
    String registerUserPwd1;
    String registerUserPwd2;
    private JTextField registerName;
    private JPasswordField registerPasswordField1;
    private JPasswordField registerPasswordField2;
    private JPanel root;
    private JButton registerButton;
    private JLabel textHeader;
    private JLabel textUserNameTip;
    private JLabel textUserPwd1Tip;
    private JLabel textUserPwd2Tip;

    public Register() {
        registerButton.addActionListener(e -> {
            registerUserName = registerName.getText();
            registerUserPwd1 = String.valueOf(registerPasswordField1.getPassword());
            registerUserPwd2 = String.valueOf(registerPasswordField2.getPassword());
            //用户点击注册时判断用户名是否为空，trim：删除前导和尾随空格
            if (registerUserName.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                //用户点击注册时判断密码1是否为空，trim：删除前导和尾随空格
            } else if (registerUserPwd1.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
                //用户点击注册时判断密码2是否为空，trim：删除前导和尾随空格
            } else if (registerUserPwd2.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
                //用户点击注册时判断密码1与密码2是否一致，trim：删除前导和尾随空格
            } else if (!registerUserPwd1.equals(registerUserPwd2)) {
                JOptionPane.showMessageDialog(null, "两次密码不一致");
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", registerUserName);
                jsonObject.put("userPwd", registerUserPwd1);
                File jsonFile = new File("config.json");
                try {
                    MyJson.writeJson(jsonObject, jsonFile);
                    toLoginFrame(registerUserName, registerUserPwd1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * 跳转登录窗口
     */
    public void toLoginFrame(String userNameJson, String userPwdJson) {
        //关闭注册窗口
        frame.dispose();
        Login login = new Login();
        login.loginRun(userNameJson, userPwdJson);
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        textHeader.setText("用户注册");
        textUserNameTip.setText("请输入用户名：");
        textUserPwd1Tip.setText("请输入注册密码：");
        textUserPwd2Tip.setText("请再次输入密码：");
        registerButton.setText("注册");
    }

    public void registerRun() {
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        //窗口居中
        initFrame(frame);
    }
}
