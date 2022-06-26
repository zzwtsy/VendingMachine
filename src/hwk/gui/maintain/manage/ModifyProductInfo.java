package hwk.gui.maintain.manage;

import hwk.utils.GetProductInfo;
import hwk.utils.SetLogo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.UpdateProductInfo.modifyInfo;
import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class ModifyProductInfo {
    private JFrame frame;
    private JPanel root;
    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productNumbersTextField;
    private int productSerial;
    private JLabel productNumbersLabel;
    private JLabel productPriceLabel;
    private JLabel productNameLabel;
    private JButton saveButton;
    private JButton cancelButton;

    public ModifyProductInfo() {
        saveButton.addActionListener(e -> {
            String productName = productNameTextField.getText();
            String productPrice = productPriceTextField.getText();
            String productNumbers = productNumbersTextField.getText();
            try {
                modifyInfo(productSerial, productName, productPrice, productNumbers);
                JOptionPane.showMessageDialog(null, "修改成功");
                frame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "修改失败");
                frame.dispose();
            }
        });
        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void setWindowText() {
        productNameLabel.setText("名称");
        productPriceLabel.setText("价格/元");
        productNumbersLabel.setText("数量/瓶");
        saveButton.setText("保存");
        cancelButton.setText("取消");
        GetProductInfo getProductInfo = new GetProductInfo();
        productNumbersTextField.setText(getProductInfo.getProductNumbers(productSerial, getProductInfo.getProductInfoJson()));
        productPriceTextField.setText(getProductInfo.getProductPrice(productSerial, getProductInfo.getProductInfoJson()));
        productNameTextField.setText(getProductInfo.getProductName(productSerial, getProductInfo.getProductInfoJson()));
    }

    public void modifyProductInfoRun(String productSerial) {
        this.productSerial = Integer.parseInt(productSerial) - 1;
        frame = new JFrame("ModifyProductInfo");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        //窗口居中
        initFrame(frame);
    }
}
