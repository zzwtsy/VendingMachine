package hwk.gui.maintain;

import hwk.gui.Menu;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.WindowCenter.initFrame;

public class SystemMaintain {
    private JFrame frame;
    private JPanel root;
    private JLabel text1;
    private JButton productControlButton;
    private JButton modifyUserPwdButton;
    private JButton displayLogButton;
    private JButton goBackButton;

    public SystemMaintain() {
        //点击返回按钮进入后台管理菜单
        goBackButton.addActionListener(e -> {
            frame.dispose();
            hwk.gui.Menu menu = new hwk.gui.Menu();
            menu.menuRun();
        });
    }

    public static void main(String[] args) {
        SystemMaintain systemMaintain = new SystemMaintain();
        systemMaintain.SystemMaintainRun();
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        text1.setText("系统维护");
        productControlButton.setText("产品管理");
        displayLogButton.setText("显示销售日志");
        modifyUserPwdButton.setText("修改管理员密码");
        goBackButton.setText("返回上级菜单");
    }

    public void SystemMaintainRun() {
        frame = new JFrame("SystemMaintain");
        frame.setContentPane(this.root);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new Menu().menuRun();
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
