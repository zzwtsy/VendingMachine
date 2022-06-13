package hwk1.gui.login;

import hwk1.gui.Menu;
import hwk1.gui.selling.Sell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SellLogin {
    private JFrame frame;
    private JLabel textHeader;
    private JTextField userNameField;
    private JPasswordField userPwdField;
    private JButton loginButton;
    private JLabel textUserNameTip;
    private JLabel textUserPwdTip;
    private JPanel root;
    private String userName;
    private String userPwd;

    public static void main(String[] args) {
        new SellLogin().loginRun("1","1");
    }
    public SellLogin() {
        loginButton.addActionListener(e -> {
            String loginUserName = userNameField.getText();
            String loginUserPwd = String.valueOf(userPwdField.getPassword());
            //判断用户点击登录时用户名是否为空，trim：删除前导和尾随空格
            if (loginUserName.trim().equals("") | !userName.equals(loginUserName) | loginUserPwd.trim().equals("") | !userPwd.equals(loginUserPwd)) {
                JOptionPane.showMessageDialog(null, "登录错误");
                frame.dispose();
                new Sell().sellRun();
            } else {
                frame.dispose();
                hwk1.gui.Menu menu = new Menu();
                menu.menuRun();
            }
        });
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        textHeader.setText("饮料售卖机");
        textUserNameTip.setText("用户名");
        textUserPwdTip.setText("密码");
        loginButton.setText("登录");
    }

    public void loginRun(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
        frame = new JFrame("Login");
        frame.setContentPane(this.root);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                new Sell().sellRun();
            }
        });
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
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
