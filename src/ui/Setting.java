package ui;

import data.*;
import util.Const;
import util.JsonUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.ZonedDateTime;

public class Setting extends JFrame {
    private final SalesLogTableModel salesLogTableModel;
    private JPanel contentPanel;
    private JTabbedPane maintainTabbedPane;
    private JScrollPane maintainScrollPanel;
    private JTable maintainTable;
    private JPanel maintainPanel;
    private JPanel addProductPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JPanel salesLogPanel;
    private JScrollPane salesLogScrollPanel;
    private JTable salesLogTable;
    private JPanel paginationPanel;
    private JPanel paginationTipPanel;
    private JLabel paginationTipText;
    private JButton prevButton;
    private JButton nextButton;
    private JComboBox<Integer> pageSizeComboBox;

    public Setting() {
        setTitle("设置");
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        pageSizeComboBox.addItem(20);
        pageSizeComboBox.addItem(50);
        pageSizeComboBox.addItem(100);
        pageSizeComboBox.addItem(200);
        pageSizeComboBox.setSelectedItem(Const.PAGE.getPageSize());
        Page.updatePage();
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
        salesLogTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        // 设置表格 Model 数据
        maintainTable.setModel(new ProductMaintenanceTableModel());
        salesLogTableModel = new SalesLogTableModel();
        salesLogTable.setModel(salesLogTableModel);
        // 设置按钮事件监听器
        deleteButton.addActionListener(e -> onDeleteButton());
        addButton.addActionListener(e -> onAddButton());
        // 添加TabbedPane的监听器，当Tab页改变时，更新对应的UI
        maintainTabbedPane.addChangeListener(e -> {
            // 获取当前选中的组件
            Component component = maintainTabbedPane.getSelectedComponent();
            // 如果选中的是销售日志面板，则更新销售日志UI
            if (component == salesLogPanel) {
                updateSalesLogUI();
            }
            // 如果选中的是维护面板，则更新维护表格UI
            if (component == maintainPanel) {
                maintainTable.updateUI();
            }
        });
        // 添加上一页按钮的监听器，当点击上一页按钮时，更新当前页码，并更新销售日志UI
        prevButton.addActionListener(e -> {
            // 如果当前页码已经是第一页，则不进行任何操作
            if (Const.PAGE.getCurrentPage() == 1) {
                return;
            }
            // 否则，将当前页码减1
            Const.PAGE.setCurrentPage(Const.PAGE.getCurrentPage() - 1);
            // 更新销售日志UI
            updateSalesLogUI();
        });
        // 添加下一页按钮的监听器，当点击下一页按钮时，更新当前页码，并更新销售日志UI
        nextButton.addActionListener(e -> {
            // 如果当前页码已经是最后一页，则不进行任何操作
            if (Const.PAGE.getCurrentPage() >= Const.PAGE.getTotalPage()) {
                return;
            }
            // 否则，将当前页码加1
            Const.PAGE.setCurrentPage(Const.PAGE.getCurrentPage() + 1);
            // 更新销售日志UI
            updateSalesLogUI();
        });
        // 添加每页显示数量下拉框的监听器，当选择每页显示数量时，更新每页显示数量，并更新销售日志UI
        pageSizeComboBox.addActionListener(e -> {
            // 获取选择的每页显示数量
            Integer selectedItem = (Integer) pageSizeComboBox.getSelectedItem();
            // 如果没有选择每页显示数量，则不进行任何操作
            if (selectedItem == null) {
                return;
            }
            // 否则，将选择的每页显示数量设置为当前每页显示数量
            Const.PAGE.setPageSize(selectedItem);
            // 更新销售日志UI
            updateSalesLogUI();
        });
        // 设置表格可编辑
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(new JTextField());
        maintainTable.setCellEditor(defaultCellEditor);
    }

    /**
     * 添加商品
     */
    public void onAddButton() {
        AddProduct.showAddProduct((product) -> {
            // 记录日志
            Log log = new Log("添加商品", ZonedDateTime.now().toString(), Const.config.getUserName(), JsonUtil.encodeToString(product));
            Log.save(log);
            ((ProductMaintenanceTableModel) maintainTable.getModel()).addProduct(product);
            Config.save();
            maintainTable.updateUI();
        });

    }

    public void onDeleteButton() {
        // 获取选中的行
        int[] selectedRows = maintainTable.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的商品", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 删除选中的行
        try {
            ((ProductMaintenanceTableModel) maintainTable.getModel()).deleteRows(selectedRows);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(this, "删除失败：" + e.getMessage(), "提示", JOptionPane.YES_NO_OPTION);
        }

        // 更新表格数据
        maintainTable.updateUI();
    }

    // 更新销售日志UI
    private void updateSalesLogUI() {
        // 刷新销售日志表格模型
        salesLogTableModel.refresh();
        // 设置分页提示文本
        paginationTipText.setText("第" + Const.PAGE.getCurrentPage() + "页 / " + "共" + Const.PAGE.getTotalPage() + "页");
        // 更新销售日志表格UI
        salesLogTable.updateUI();
    }

    public static void showSetting() {
        Setting setting = new Setting();
        setting.pack();
        setting.setVisible(true);
        setting.setSize(600, 400);
    }
}
