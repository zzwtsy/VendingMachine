package hwk.gui.selling;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.ProductSell.pay;
import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class InsertCoins {
    private JFrame frame;
    private JPanel root;
    private JLabel payMoney;
    private JTextField userPayMoneyField;
    private JButton buyButton;
    private JButton cancelButton;
    private JPanel showPanel;
    private float accountsPayable;

    public InsertCoins() {
        buyButton.addActionListener(e -> onBuyButton());
        cancelButton.addActionListener(e -> {
            frame.dispose();
            new Sell().sellRun();
        });
    }

    private void onBuyButton() {
        frame.dispose();
        float userPayMoney = Float.parseFloat(userPayMoneyField.getText());
        if (pay(userPayMoney, accountsPayable) == 0) {
            JOptionPane.showMessageDialog(null, "请取走您的饮料，欢迎下次光临");
        } else if (pay(userPayMoney, accountsPayable) == -1) {
            JOptionPane.showMessageDialog(null, "购买失败请重新购买");
        } else {
            JOptionPane.showMessageDialog(null, "找零" + pay(userPayMoney, accountsPayable) + "元，请取走您的饮料");
        }
        new Sell().sellRun();
    }

    private void setWindowText() {
        payMoney.setText("请付款" + accountsPayable + "元");
        buyButton.setText("购买");
        cancelButton.setText("取消");
    }

    public void insertCoinsRun(float accountsPayable) {
        this.accountsPayable = accountsPayable;
        frame = new JFrame("InsertCoinsTemp");
        frame.setContentPane(this.root);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });
        frame.pack();
        frame.setVisible(true);
        setWindowText();
        //窗口居中
        initFrame(frame);
    }
}
