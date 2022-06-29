package hwk.gui.login;

import hwk.gui.Menu;
import hwk.gui.selling.Sell;
import hwk.utils.SetLogo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
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

    /**
     * SellLogin 监听事件
     */
    public SellLogin() {
        loginButton.addActionListener(e -> {
            String loginUserName = userNameField.getText();
            String loginUserPwd = String.valueOf(userPwdField.getPassword());
            //判断用户点击登录时用户名是否为空，trim：删除前导和尾随空格
            if ("".equals(loginUserName.trim()) | !userName.equals(loginUserName) | "".equals(loginUserPwd.trim()) | !userPwd.equals(loginUserPwd)) {
                JOptionPane.showMessageDialog(null, "登录错误");
                frame.dispose();
                new Sell().sellRun();
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

    /**
     * SellLogin 入口
     *
     * @param userName 用户名
     * @param userPwd  用户密码
     */
    public void sellLoginRun(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
        frame = new JFrame("Login");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Sell().sellRun();
            }
        });
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        //窗口居中
        initFrame(frame);
    }
}
