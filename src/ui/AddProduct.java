package ui;

import data.Config;
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

    // 点击确定按钮时执行的方法
    private void onOK() {
        // 获取商品名称
        String productName = textFieldProductName.getText().trim();
        // 判断商品名称是否已存在
        boolean productNameExists = Const.config.getProductList().stream().anyMatch(e -> e.getProductName().equals(productName));
        // 如果商品名称已存在，弹出提示框
        if (productNameExists) {
            JOptionPane.showConfirmDialog(this, "商品名称已存在", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 获取商品价格
        double price = (Double) textFieldProductPrice.getValue();
        // 获取商品数量
        int quantity = (Integer) textFieldProductQuantity.getValue();

        // 判断商品名称是否为空
        if (productName.isEmpty()) {
            JOptionPane.showConfirmDialog(this, "请输入商品名称", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        // 判断商品价格是否为0
        } else if (price == 0) {
            JOptionPane.showConfirmDialog(this, "请输入商品价格", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        // 判断商品数量是否为0
        } else if (quantity == 0) {
            JOptionPane.showConfirmDialog(this, "请输入商品数量", "提示", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
        // 如果商品名称、价格、数量都不为空，则创建商品对象，并添加到商品列表中
        } else {
            Product product = new Product();
            // 设置商品编码
            int productCode = Const.config.getProductList().size() + 1;
            product.setProductCode(String.valueOf(productCode));
            // 设置商品名称
            product.setProductName(productName);
            // 设置商品价格
            product.setPrice(price);
            // 设置商品数量
            product.setQuantity(quantity);
            // 添加商品到商品列表中
            onAddProduct.accept(product);
            Config.save();
        }
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
