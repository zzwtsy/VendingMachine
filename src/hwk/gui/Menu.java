package hwk.gui;

import hwk.gui.maintain.SystemMaintain;
import hwk.gui.selling.Sell;
import hwk.utils.SetLogo;

import javax.swing.*;

import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
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
            new Initialize().initializeRun();
        });
        //点击系统维护按钮进入系统维护界面
        maintainSystemButton.addActionListener(e -> {
            frame.dispose();
            new SystemMaintain().systemMaintainRun();
        });
        sellSystemButton.addActionListener(e -> {
            frame.dispose();
            new Sell().sellRun();
        });
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        textHeader.setText("后台管理菜单");
        maintainSystemButton.setText("系统维护");
        sellSystemButton.setText("售卖界面");
        initSystemButton.setText("初始化系统");
        exitSystemButton.setText("退出程序");
    }

    public void menuRun() {
        frame = new JFrame("Menu");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //设置窗口文字
        setWindowText();
        frame.setVisible(true);
        //窗口居中
        initFrame(frame);
    }
}
