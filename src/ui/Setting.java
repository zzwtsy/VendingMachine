package ui;

import data.ProductMaintenanceTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class Setting extends JFrame {
    private JPanel contentPanel;
    private JTabbedPane maintainTabbedPane;
    private JScrollPane maintainScrollPanel;
    private JTable maintainTable;
    private JScrollPane salesLogPanel;
    private JPanel maintainPanel;
    private JPanel addProductPanel;
    private JButton addButton;
    private JButton deleteButton;

    public Setting() {
        setTitle("设置");
        setContentPane(contentPanel);
        // 点击 X 时退出程序
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // 设置表格不可拖动和调整列宽
        JTableHeader tableHeader = maintainTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        // 设置表格渲染器
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        // 设置表格内容居中
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        defaultTableCellRenderer.setVerticalAlignment(JLabel.CENTER);
        maintainTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        // 设置按钮事件监听器
        deleteButton.addActionListener(e -> onDeleteButton());
        addButton.addActionListener(e -> onAddButton());
    }

    /**
     * 添加商品
     */
    public void onAddButton() {

    }

    public void onDeleteButton() {
        // 获取选中的行
        int selectedRow = maintainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的商品", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 删除选中的行
        boolean deleted = ((ProductMaintenanceTableModel) maintainTable.getModel()).deleteRow(selectedRow);
        if (deleted) {
            updateMaintainTableData();
            JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "删除失败", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 更新商品表格数据
     */
    public void updateMaintainTableData() {
        maintainTable.setModel(new ProductMaintenanceTableModel());
    }

    public static void showSetting() {
        Setting setting = new Setting();
        setting.pack();
        setting.setVisible(true);
        setting.setSize(600, 400);
        setting.updateMaintainTableData();
    }
}
