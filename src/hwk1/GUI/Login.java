package hwk1.GUI;

import javax.swing.*;

public class Login {
    private JTextField nameField;
    private JPasswordField pwdField;
    private JButton loginButton;
    private JFrame frame;
    private JPanel root;
    private String userName;
    private String userPwd;

    public Login() {
        loginButton.addActionListener(e -> {
            String loginUserName = nameField.getText();
            String loginUserPwd = String.valueOf(pwdField.getPassword());
            //判断用户点击登录时用户名是否为空，trim：删除前导和尾随空格
            if (loginUserName.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
            } else if (!userName.equals(loginUserName)) {
                JOptionPane.showMessageDialog(null, "用户名错误");
                //判断用户点击登录时密码是否为空，trim：删除前导和尾随空格
            } else if (loginUserPwd.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户密码不能为空");
            } else if (!userPwd.equals(loginUserPwd)) {
                JOptionPane.showMessageDialog(null, "用户密码错误");
            } else{
                frame.dispose();
                Menu menu = new Menu();
                menu.menuRun();
            }
        });
    }

    public void loginRun(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
        frame = new JFrame("Login");
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
