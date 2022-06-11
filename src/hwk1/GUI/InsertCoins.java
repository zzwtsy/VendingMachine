package hwk1.GUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertCoins extends JDialog {
    private JPanel contentPane;
    private JButton buyButton;
    private JButton cancelButton;
    private JLabel payMoney;
    private JTextField textField1;
    private JPanel showPanel;
    private JPanel root;

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

    public static void main(String[] args) {
        new InsertCoins().insertCoinsRun();
    }

    private void onOK() {
        // 在此处添加您的代码
        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    public void insertCoinsRun() {
        InsertCoins dialog = new InsertCoins();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
