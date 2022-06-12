package hwk1.GUI;

import hwk1.productSell;

import javax.swing.*;

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
        cancelButton.addActionListener(e -> frame.dispose());
    }

    private void onBuyButton() {
        frame.dispose();
        float userPayMoney = Float.parseFloat(userPayMoneyField.getText());
        if (new productSell().pay(userPayMoney,accountsPayable) == 0){
            JOptionPane.showMessageDialog(null, "请取走您的饮料，欢迎下次光临");
        } else if (new productSell().pay(userPayMoney,accountsPayable) == -1) {
            JOptionPane.showMessageDialog(null, "购买失败请重新购买");
        }else {
            JOptionPane.showMessageDialog(null, "找零" + new productSell().pay(userPayMoney,accountsPayable) + "元，请取走您的饮料");
        }
    }

    private void setWindowText(){
        payMoney.setText("请付款" + accountsPayable + "元");
        buyButton.setText("购买");
        cancelButton.setText("取消");
    }

    public static void main(String[] args) {
        new InsertCoins().insertCoinsRun(1);
    }

    public void insertCoinsRun(float accountsPayable){
        this.accountsPayable = accountsPayable;
        frame = new JFrame("InsertCoinsTemp");
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        setWindowText();
    }
}
