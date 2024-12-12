package data;


import util.Const;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 维护销售产品表格数据
 */
public class ProductMaintenanceTableModel extends VendingTableModel {
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0 && columnIndex != 1;
    }

    /**
     * 删除指定行
     *
     * @param rowIndex 行索引
     * @return 删除成功返回 true，否则返回 false
     */
    public boolean deleteRow(int rowIndex) {
        String productCode = super.tableData.get(rowIndex).getProductCode();
        boolean result = false;
        ArrayList<Product> productList = new ArrayList<>(Const.config.getProductList());
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductCode().equals(productCode)) {
                iterator.remove();
                result = true;
            }
        }
        Const.config.setProductList(productList);
        Config.save();
        return result;
    }

    /**
     * 添加商品
     */
    public void addProduct(Product product) {
        Const.config.getProductList().add(product);
    }

}
