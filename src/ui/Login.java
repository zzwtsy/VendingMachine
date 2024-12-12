package ui;

import util.Const;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Customizer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textFieldUserName;
    private JPasswordField passwordFieldUserPwd;
    private JLabel textUserName;
    private JLabel textUserPwd;
    private JButton buttonCancel;
    private static Runnable oKCallback;

    public Login() {
        setTitle("登录");
        setContentPane(contentPane);
        setModal(true);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        boolean userNameOk = textFieldUserName.getText().trim().equals(Const.config.getUserName());
        boolean userPwdOk = String.valueOf(passwordFieldUserPwd.getPassword()).trim().equals(Const.config.getPassword());
        if (userNameOk && userPwdOk) {
            oKCallback.run();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    /**
     * 设置窗口大小
     *
     * @param oKCallback 点击确定成功时执行的回调函数
     */
    public static void showLogin(Runnable oKCallback) {
        Login dialog = new Login();
        Login.oKCallback = oKCallback;
        dialog.setPreferredSize(new Dimension(400, 300));
        dialog.pack();
        dialog.setVisible(true);
    }
}
