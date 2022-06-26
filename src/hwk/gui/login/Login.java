package hwk.gui.login;

import hwk.gui.Menu;
import hwk.utils.SetLogo;

import javax.swing.*;

import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class Login {
    private JTextField nameField;
    private JPasswordField pwdField;
    private JButton loginButton;
    private JFrame frame;
    private JPanel root;
    private JLabel textHeader;
    private JLabel textUserNameTip;
    private JLabel textUserPwdTip;
    private String userName;
    private String userPwd;

    public Login() {
        loginButton.addActionListener(e -> {
            String loginUserName = nameField.getText();
            String loginUserPwd = String.valueOf(pwdField.getPassword());
            //判断用户点击登录时用户名是否为空，trim：删除前导和尾随空格
            if ("".equals(loginUserName.trim())) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
            } else if (!userName.equals(loginUserName)) {
                JOptionPane.showMessageDialog(null, "用户名错误");
                //判断用户点击登录时密码是否为空，trim：删除前导和尾随空格
            } else if ("".equals(loginUserPwd.trim())) {
                JOptionPane.showMessageDialog(null, "用户密码不能为空");
            } else if (!userPwd.equals(loginUserPwd)) {
                JOptionPane.showMessageDialog(null, "用户密码错误");
            } else {
                frame.dispose();
                hwk.gui.Menu menu = new Menu();
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
        //设置图标
        new SetLogo().setIconImage(frame);
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
