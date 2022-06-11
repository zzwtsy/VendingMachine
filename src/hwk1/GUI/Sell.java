package hwk1.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sell {
    private JPanel root;
    private JLabel textHeader;
    private JPanel showPanel;
    private JPanel choicePanel;
    private JButton buyButton;
    private JTextField textField1;
    private JList serialNumberList;
    private JList nameList;
    private JList priceList;
    private JPanel serialNumberPanel;
    private JList numberList;
    private JPanel namePanel;
    private JPanel pricePanel;
    private JPanel numberPanel;

    public Sell() {
        buyButton.addActionListener(e -> {
                new InsertCoins().insertCoinsRun();
        });
    }

    private void setWindowText(){
        textHeader.setText("自动售卖机");
    }

    public static void main(String[] args) {
        new Sell().sellRun();
    }

    public void sellRun() {
        JFrame frame = new JFrame("Sell");
        frame.setContentPane(new Sell().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
