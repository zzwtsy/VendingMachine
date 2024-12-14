package ui;

import data.Config;
import data.VendingTableModel;
import util.Const;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class Vending extends JFrame {
    private JPanel contentPanel;
    private JTable vendingTable;
    private JScrollPane tableScrollPanel;
    private JButton buyButton;
    private JSpinner buySpinner;
    private JLabel buyGoodNumber;
    private JPanel buyPanel;

    public Vending() {
        setTitle("自动售货机");
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        // 点击 X 时退出程序
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 设置表格
        JTableHeader tableHeader = vendingTable.getTableHeader();
        // 设置表头不可拖动
        tableHeader.setReorderingAllowed(false);
        // 设置表头不可调整大小
        tableHeader.setResizingAllowed(false);
        // 设置表格渲染器
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        // 设置表格内容居中
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        defaultTableCellRenderer.setVerticalAlignment(JLabel.CENTER);
        vendingTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        // 设置表格选择模式
        ListSelectionModel listSelectionModel = getListSelectionModel();
        vendingTable.setSelectionModel(listSelectionModel);
        // 设置购买按钮点击事件
        buyButton.addActionListener(this::onOk);
        // 设置购买数量选择器
        buySpinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
    }

    private void onOk(ActionEvent e) {
        // 获取选中的行
        int selectedRow = vendingTable.getSelectedRow();
        // 如果未选中行，则不能购买
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(contentPanel, "请先选择商品", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 获取选中的商品数量
        Object goodNumber = vendingTable.getValueAt(selectedRow, 3);
        // 如果商品数量为 0，则不能购买
        if (goodNumber.toString().equals("0")) {
            // 获取商品名称
            String goodName = (String) vendingTable.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(contentPanel, goodName + "已售罄", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 获取选中的商品编号
        String goodId = (String) vendingTable.getValueAt(selectedRow, 0);
        // 获取购买数量
        int count = (int) buySpinner.getValue();
        Const.config.getProductList().forEach(product -> {
            if (Objects.equals(product.getProductCode(), goodId)) {
                // 如果购买数量大于商品数量，则不能购买
                if (count > product.getQuantity()) {
                    JOptionPane.showMessageDialog(contentPanel, "商品数量不足", "提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    // 购买商品
                    product.setQuantity(product.getQuantity() - count);
                    JOptionPane.showMessageDialog(contentPanel, "购买成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // 保存配置
        Config.save();
        // 更新表格数据
        vendingTable.updateUI();
    }

    /**
     * 设置表格选择模式
     *
     * @return ListSelectionModel
     */
    private ListSelectionModel getListSelectionModel() {
        ListSelectionModel listSelectionModel = new DefaultListSelectionModel() {
            @Override
            public void setSelectionInterval(int index0, int index1) {
                // 商品数量
                Object goodNumber = vendingTable.getValueAt(index0, 3);
                // 如果商品数量不为 0，则可以选择
                if (!goodNumber.toString().equals("0")) {
                    super.setSelectionInterval(index0, index1);
                }
            }

        };
        // 设置选择模式为单选
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listSelectionModel;
    }

    public static void showVendingWindow() {
        Vending vending = new Vending();
        vending.pack();
        vending.setVisible(true);
        vending.setSize(600, 400);
        vending.vendingTable.setModel(new VendingTableModel());
    }

}
