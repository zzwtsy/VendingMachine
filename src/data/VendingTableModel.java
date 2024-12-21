package data;

import util.Const;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * 商品售卖表格数据
 */
public class VendingTableModel extends AbstractTableModel {
    protected final Vector<String> tableTitle;
    protected final Vector<Product> tableData = Const.config.productListToVector();

    public VendingTableModel() {
        this.tableTitle = new Vector<>();
        tableTitle.add("商品编号");
        tableTitle.add("商品名称");
        tableTitle.add("商品价格");
        tableTitle.add("商品数量");
    }

    @Override
    public int getRowCount() {
        return tableData.size();
    }

    @Override
    public int getColumnCount() {
        return tableTitle.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = tableData.get(rowIndex);
        // 根据列索引返回对应的数据
        return switch (columnIndex) {
            case 0 -> product.getProductCode();
            case 1 -> product.getProductName();
            case 2 -> product.getPrice();
            case 3 -> product.getQuantity();
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return tableTitle.get(column);
    }
}
