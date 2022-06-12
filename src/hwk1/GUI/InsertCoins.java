package hwk1.GUI;

import com.google.gson.Gson;
import hwk1.MyJson;
import hwk1.productSell;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Map;

public class InsertCoins extends JDialog {
    private JPanel contentPane;
    private JButton buyButton;
    private JButton cancelButton;
    private JLabel payMoney;
    private JTextField userPayMoneyField;
    private JPanel showPanel;
    private JPanel root;
    private float accountsPayable;


    public InsertCoins() {
        setContentPane(contentPane);
        setModal(true);

        buyButton.addActionListener(e -> onOK());

        cancelButton.addActionListener(e -> onCancel());

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        dispose();
        float userPayMoney = Float.parseFloat(userPayMoneyField.getText());
        if (new productSell().pay(userPayMoney,accountsPayable) == 0){
            JOptionPane.showMessageDialog(null, "请取走您的饮料，欢迎下次光临");
        } else if (new productSell().pay(userPayMoney,accountsPayable) == -1) {
            JOptionPane.showMessageDialog(null, "购买失败请重新购买");
        }else {
            JOptionPane.showMessageDialog(null, "找零" + new productSell().pay(userPayMoney,accountsPayable) + "元，请取走您的饮料");
        }
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText(){
        payMoney.setText("请付款" + accountsPayable + "元");
    }

    private void onCancel() {
        dispose();
    }



    public void insertCoinsRun(float accountsPayable) {
        this.accountsPayable = accountsPayable;
        InsertCoins dialog = new InsertCoins();
        //设置窗口文字
        setWindowText();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
