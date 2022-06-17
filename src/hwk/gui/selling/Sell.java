package hwk.gui.selling;

import hwk.gui.login.SellLogin;
import hwk.utils.GetProductInfo;
import hwk.utils.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.File;

import static hwk.utils.UpdateProductInfo.updateProductNumbers;
import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class Sell {
    GetProductInfo getProductInfo;
    private JFrame frame;
    private JPanel root;
    private JLabel textHeader;
    @SuppressWarnings("all")
    private JPanel choicePanel;
    private JButton buyButton;
    private JTextField serialNumberField;
    private JTextField numbersField;
    private JLabel buyNumbersLabel;
    private JLabel productSerialNumberLabel;
    private JTable table;
    @SuppressWarnings("all")
    private JScrollPane content;
    private JButton manageButton;
    private float productPrice;
    private String[][] data = null;
    private int n;
    //产品序号

    public Sell() {
        buyButton.addActionListener(e -> {
            //获取用户购买产品数量
            int userProductNumbers = Integer.parseInt(numbersField.getText());
            //获取用户选择的产品序号
            try {
                n = Integer.parseInt(serialNumberField.getText()) - 1;
                //当前用户选择产品的剩余数量
                int productNumbers = 3;
                if (n + 1 <= 0 | n + 1 > data.length) {
                    JOptionPane.showMessageDialog(null, "暂无此商品序号");
                } else if (userProductNumbers <= 0 | userProductNumbers > Integer.parseInt(data[n][productNumbers])) {
                    JOptionPane.showMessageDialog(null, "购买的商品数量错误");
                } else {
                    Object tempProductPrice = getProductInfo.getProductInfoJson().getJSONObject(String.valueOf(n)).get("productPrice");
                    productPrice = Float.parseFloat((String) tempProductPrice);
                    float accountsPayable = productPrice * userProductNumbers;
                    updateProductNumbers(n, userProductNumbers);
                    frame.dispose();
                    new InsertCoins().insertCoinsRun(accountsPayable);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "请输入要购买的商品序号");
            }

        });
        manageButton.addActionListener(e -> {
            frame.dispose();
            File file = new File("config.json");
            JSONObject contentJson = (JSONObject) MyJson.readJson(file);
            String userNameJson = (String) contentJson.get("userName");
            String userPwdJson = (String) contentJson.get("userPwd");
            frame.dispose();
            new SellLogin().loginRun(userNameJson, userPwdJson);
        });
    }

    private void setWindowText() {
        textHeader.setText("饮料售卖机");
        buyButton.setText("购买");
        productSerialNumberLabel.setText("对应序号：");
        buyNumbersLabel.setText("购买数量：");
        textHeader.setText("自动售卖机");
        numbersField.setText("1");
        manageButton.setText("后台管理");
    }

    public void sellRun() {
        getProductInfo = new GetProductInfo();
        String[] name = {"序号", "名称", "价格/元", "数量/瓶"};
        frame = new JFrame("Sell");
        frame.setContentPane(this.root);
        //通过GetProductInfo方法获取产品信息存入到data中
        data = getProductInfo.getProductInfoData(getProductInfo.getProductInfoJson());
        table.setModel(new DefaultTableModel(data, name));
        //表格文字只读
        table.setEnabled(false);
        //文字居中
        DefaultTableCellRenderer fontCenter = new DefaultTableCellRenderer();
        fontCenter.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, fontCenter);
        //点击X结束系统运行
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        frame.setVisible(true);
        //窗口居中
        initFrame(frame);
    }
}
