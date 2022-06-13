package hwk1.gui.maintain;

import hwk1.gui.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
            hwk1.gui.Menu menu = new hwk1.gui.Menu();
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
                hwk1.gui.Menu menu = new Menu();
                menu.menuRun();
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
