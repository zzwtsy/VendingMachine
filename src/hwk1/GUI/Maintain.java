package hwk1.GUI;

import javax.swing.*;
import java.awt.*;

public class Maintain {
    private JPanel root;
    private JButton displayLogButton;
    private JButton productControlButton;
    private JButton modifyUserPwdButton;
    private JButton goBackButton;

    public Maintain() {
        goBackButton.addActionListener(e -> {
            Menu menu =new Menu();
            menu.menuRun();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maintain");
        frame.setContentPane(new Maintain().root);
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
