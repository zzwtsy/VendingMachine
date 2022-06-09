package hwk1.GUI;

import javax.swing.*;
import java.awt.*;

public class Register {
    private JTextField registerName;
    private JPasswordField registerPasswordField1;
    private JPasswordField registerPasswordField2;
    private JFrame frame;
    private JPanel root;
    private JButton registerButton;

    public Register() {
        registerButton.addActionListener(e -> {
            String registerUserName = registerName.getText();
            String registerUserPwd1 = String.valueOf(registerPasswordField1.getPassword());
            String registerUserPwd2 = String.valueOf(registerPasswordField2.getPassword());
            //用户点击注册时判断用户名是否为空，trim：删除前导和尾随空格
            if (registerUserName.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
            }
            //用户点击注册时判断密码1是否为空，trim：删除前导和尾随空格
            if (registerUserPwd1.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
            }
            //用户点击注册时判断密码2是否为空，trim：删除前导和尾随空格
            if (registerUserPwd2.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
            }
            //用户点击注册时判断密码1与密码2是否一致，trim：删除前导和尾随空格
            if (!registerUserPwd1.equals(registerUserPwd2)) {
                JOptionPane.showMessageDialog(null, "两次密码不一致");
            }
            //当用户名密码都正确填写时进入登录界面
            if (!registerUserName.trim().equals("") && registerUserPwd1.equals(registerUserPwd2)) {
                //关闭注册窗口
                frame.dispose();
                Login login = new Login();
                login.loginRun(registerUserName, registerUserPwd1);
            }
        });
    }

    public static void main(String[] args) {
        Register register = new Register();
        register.registerRun();
    }

    public void registerRun() {
        frame = new JFrame("register");
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // 获得窗口宽
        int windowWidth = frame.getWidth();
        // 获得窗口高
        int windowHeight = frame.getHeight();
        // 定义工具包
        Toolkit kit = Toolkit.getDefaultToolkit();
        // 获取屏幕的尺寸
        Dimension screenSize = kit.getScreenSize();
        // 获取屏幕的宽
        int screenWidth = screenSize.width;
        // 获取屏幕的高
        int screenHeight = screenSize.height;
        // 设置窗口居中显示
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
