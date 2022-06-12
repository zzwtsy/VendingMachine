package hwk1.GUI;

import hwk1.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Sell {
    private JPanel root;
    private JLabel textHeader;
    private JPanel showPanel;
    private JPanel choicePanel;
    private JButton buyButton;
    private JTextField serialNumberField;
    private JList serialNumberList;
    private JList nameList;
    private JList priceList;
    private JPanel serialNumberPanel;
    private JList numberList;
    private JPanel namePanel;
    private JPanel pricePanel;
    private JPanel numberPanel;
    private JTextField numbersField;
    private JLabel serialNumberLabel;
    private JLabel nameLable;
    private JLabel priceLable;
    private JLabel numbersLable;
    private JLabel buyNumbersLable;
    private JLabel buySerialNumberLable;
    private  float productPrice;
    //产品序号
    private int serialNumber;

    public Sell() {
        buyButton.addActionListener(e -> {
            //获取用户购买产品数量
            int productNumbers = Integer.parseInt(numbersField.getText());
            getProductPrice();
            float accountsPayable = productPrice * productNumbers;
            new InsertCoins().insertCoinsRun(accountsPayable);
        });
    }

    public static void main(String[] args) {
        new Sell().sellRun();
    }

    //获取产品价格
    private void getProductPrice() {
        File file = new File("product.json");
        JSONObject contentJson = (JSONObject) MyJson.readJson(file);
        @SuppressWarnings("all")
        int n = Integer.parseInt(serialNumberField.getText()) - 1;
        //产品价格
        Object tempProductPrice = contentJson.getJSONObject(String.valueOf(n)).get("productPrice");
        productPrice = Float.parseFloat((String) tempProductPrice);
    }

    private void setWindowText() {
        textHeader.setText("自动售卖机");
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
