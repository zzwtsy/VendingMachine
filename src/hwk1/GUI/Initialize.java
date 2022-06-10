package hwk1.GUI;

import javax.swing.*;
import java.awt.*;

public class Initialize {
    private JFrame frame;
    private JPanel root;
    private JTextField initTextField;
    private JButton saveButton;
    private JButton goBackButton;

    public Initialize() {
        saveButton.addActionListener(e -> {
            String init = initTextField.getText();
            if (!init.trim().equals("")) {
                String[] data = init.split("\\|");
                //商品名称，价格
                String[][] namePrice = new String[data.length][2];
                //商品数量
                int[] number = new int[data.length];
                for (int i = 0; i < data.length; i++) {
                    String[] detail = data[i].split(":");
                    //商品名称
                    namePrice[i][0] = detail[0];
                    //商品价格
                    namePrice[i][1] = detail[1];
                    //商品数量
                    number[i] = Integer.parseInt(detail[2]);
                }
            } else {
                JOptionPane.showMessageDialog(null, "保存内容不能为空");
            }
        });
        goBackButton.addActionListener(e -> {
            frame.dispose();
            Menu menu = new Menu();
            menu.menuRun();
        });
    }

    public void initializeRun() {
        frame = new JFrame("Initialize");
        frame.setContentPane(this.root);
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

    public static void main(String[] args) {
        Initialize initialize = new Initialize();
        initialize.initializeRun();
    }
}
