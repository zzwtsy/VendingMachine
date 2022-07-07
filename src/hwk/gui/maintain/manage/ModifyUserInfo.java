package hwk.gui.maintain.manage;

import hwk.gui.maintain.SystemMaintain;
import hwk.utils.CheckRegistered;
import hwk.utils.MyJson;
import hwk.utils.SetLogo;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class ModifyUserInfo {
    private JFrame frame;
    private JPasswordField userPwdField1;
    private JPasswordField userPwdField2;
    private JPanel root;
    private JLabel userPwdLabel1;
    private JLabel userPwdLabel2;
    private JButton okButton;
    private JButton cancelButton;

    /**
     * ModifyUserInfo 监听事件
     */
    public ModifyUserInfo() {
        okButton.addActionListener(e -> {
            CheckRegistered checkRegistered = new CheckRegistered();
            checkRegistered.checkRegistered();
            String userName = checkRegistered.getUserName();
            String userPwd1 = String.valueOf(userPwdField1.getPassword());
            String userPwd2 = String.valueOf(userPwdField2.getPassword());
            if (userPwd1.equals(userPwd2)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", userName);
                //对用户密码进行BCrypt加密
                jsonObject.put("userPwd", BCrypt.hashpw(userPwd1,BCrypt.gensalt()));
                File jsonFile = new File("config.json");
                try {
                    MyJson.writeJson(jsonObject, jsonFile);
                    JOptionPane.showMessageDialog(null, "修改成功");
                    frame.dispose();
                    new SystemMaintain().systemMaintainRun();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new SystemMaintain().systemMaintainRun();
        });
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        userPwdLabel1.setText("修改密码");
        userPwdLabel2.setText("请再次输入密码");
        okButton.setText("修改");
        cancelButton.setText("取消");
    }

    /**
     * ModifyUserInfo 入口
     */
    public void modifyUserInfoRun() {
        frame = new JFrame("ModifyUserInfo");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new SystemMaintain().systemMaintainRun();
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
