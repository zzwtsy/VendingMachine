package hwk1.GUI.selling;

import hwk1.tools.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class Sell {
    private JPanel root;
    private JLabel textHeader;
    @SuppressWarnings("all")
    private JPanel choicePanel;
    private JButton buyButton;
    private JTextField serialNumberField;
    private JTextField numbersField;
    private JLabel buyNumbersLable;
    private JLabel productSerialNumberLable;
    private JTable table;
    @SuppressWarnings("all")
    private JScrollPane content;
    private float productPrice;
    private String[][] data = null;
    //产品序号

    public Sell() {
        buyButton.addActionListener(e -> {
            //获取用户购买产品数量
            int productNumbers = Integer.parseInt(numbersField.getText());
            //获取用户选择的产品序号
            int n = Integer.parseInt(serialNumberField.getText()) - 1;
            Object tempProductPrice = getProductInfo().getJSONObject(String.valueOf(n)).get("productPrice");
            productPrice = Float.parseFloat((String) tempProductPrice);
            float accountsPayable = productPrice * productNumbers;
            new InsertCoins().insertCoinsRun(accountsPayable);
        });
    }

    public static void main(String[] args) {
        new Sell().sellRun();
    }

    /**
     * 获取产品信息
     */
    public JSONObject getProductInfo() {
        File file = new File("product.json");
        return (JSONObject) MyJson.readJson(file);
    }

    private void setWindowText() {
        textHeader.setText("自动售卖机");
        buyButton.setText("购买");
        productSerialNumberLable.setText("对应序号：");
        buyNumbersLable.setText("购买数量：");
        textHeader.setText("自动售卖机");
    }

    public void sellRun() {
        String[] value = {"序号", "名称", "价格/元", "数量/瓶"};
        getTableInfo();
        JFrame frame = new JFrame("Sell");
        frame.setContentPane(this.root);
        table.setModel(new DefaultTableModel(data, value));
        //表格文字只读
        table.setEnabled(false);
        //文字居中
        DefaultTableCellRenderer fontCenter=new DefaultTableCellRenderer();
        fontCenter.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, fontCenter);
        //点击X结束系统运行
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

    private void getTableInfo() {
        JSONObject contentJson = new Sell().getProductInfo();
        data = new String[contentJson.length()][4];
        for (int i = 0; i < contentJson.length(); i++) {
            String n = String.valueOf(i);
            String productPrice = (String) contentJson.getJSONObject(n).get("productPrice");
            String productNumbers = (String) contentJson.getJSONObject(n).get("productNumbers");
            String productName = (String) contentJson.getJSONObject(n).get("productName");
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = productName;
            data[i][2] = productPrice;
            data[i][3] = productNumbers;
        }
    }
}
