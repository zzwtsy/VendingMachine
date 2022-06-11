package hwk1.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JFrame frame;
    private JPanel root;
    private JButton maintainSystemButton;
    private JButton sellSystemButton;
    private JButton initSystemButton;
    private JButton exitSystemButton;
    private JLabel textHeader;

    public Menu() {
        //点击退出按钮结束程序运行
        exitSystemButton.addActionListener(e -> System.exit(0));
        //点击初始化系统按钮打开初始化系统窗口
        initSystemButton.addActionListener(e -> {
            frame.dispose();
            Initialize initialize = new Initialize();
            initialize.initializeRun();
        });
        //点击系统维护按钮进入系统维护界面
        maintainSystemButton.addActionListener(e -> {
            frame.dispose();
            SystemMaintain systemMaintain = new SystemMaintain();
            systemMaintain.SystemMaintainRun();
        });
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText(){
        textHeader.setText("后台管理菜单");
        maintainSystemButton.setText("系统维护");
        sellSystemButton.setText("售卖界面");
        initSystemButton.setText("初始化系统");
        exitSystemButton.setText("退出程序");
    }

    public void menuRun() {
        frame = new JFrame("Menu");
        frame.setContentPane(this.root);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口文字
        setWindowText();
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
