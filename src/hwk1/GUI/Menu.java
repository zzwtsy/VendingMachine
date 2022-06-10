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

    public Menu() {
        exitSystemButton.addActionListener(e -> System.exit(0));
        initSystemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Initialize initialize = new Initialize();
                initialize.initializeRun();
            }
        });
    }

    public void menuRun() {
        frame = new JFrame("Menu");
        frame.setContentPane(this.root);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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
