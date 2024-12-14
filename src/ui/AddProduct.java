package ui;

import data.Product;
import util.Const;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AddProduct extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldProductName;
    private JSpinner textFieldProductPrice;
    private JSpinner textFieldProductQuantity;
    private JLabel labelProductName;
    private JLabel labelProductPrice;
    private JLabel labelProductQuantity;
    private static Consumer<Product> onAddProduct;

    public AddProduct() {
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("添加商品");

        textFieldProductPrice.setModel(new SpinnerNumberModel(0.00, 0, 100, 0.5));
        textFieldProductQuantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

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
        String productName = textFieldProductName.getText().trim();
        boolean productNameExists = Const.config.getProductList().stream().anyMatch(e -> e.getProductName().equals(productName));
        if (productNameExists) {
            JOptionPane.showConfirmDialog(this, "商品名称已存在", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        double price = (Double) textFieldProductPrice.getValue();
        int quantity = (Integer) textFieldProductQuantity.getValue();

        if (productName.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "请输入商品名称", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        } else if (price == 0) {
            JOptionPane.showConfirmDialog(this, "请输入商品价格", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        } else if (quantity == 0) {
            JOptionPane.showConfirmDialog(this, "请输入商品数量", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        } else {
            Product product = new Product();
            int productCode = Const.config.getProductList().size() + 1;
            product.setProductCode(String.valueOf(productCode));
            product.setProductName(productName);
            product.setPrice(price);
            product.setQuantity(quantity);
            onAddProduct.accept(product);
        }

        dispose();
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }

    public static void showAddProduct(Consumer<Product> onAddProduct) {
        AddProduct.onAddProduct = onAddProduct;
        AddProduct dialog = new AddProduct();
        dialog.pack();
        dialog.setVisible(true);
    }
}
