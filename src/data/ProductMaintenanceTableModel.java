package data;


import util.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

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
     */
    public void deleteRows(int[] rowIndex) {
        // 将行索引转换为产品代码列表
        List<String> productCodeList = Arrays.stream(rowIndex).mapToObj(i -> super.tableData.get(i).getProductCode()).toList();

        // 获取产品列表
        ArrayList<Product> productList = new ArrayList<>(Const.config.getProductList());
        // 创建迭代器
        Iterator<Product> iterator = productList.iterator();

        // 遍历产品代码列表
        for (String productCode : productCodeList) {
            // 获取产品
            Product product = iterator.next();
            // 如果产品代码匹配
            if (product.getProductCode().equals(productCode)) {
                // 从产品列表中移除产品
                iterator.remove();
                // 从表格数据中移除产品
                tableData.remove(product);
            }
        }

        // 更新产品列表
        Const.config.setProductList(productList);
        // 保存配置
        Config.save();
    }


    /**
     * 添加商品
     */
    public void addProduct(Product product) {
        ArrayList<Product> productList = new ArrayList<>(Const.config.getProductList());
        productList.add(product);
        Const.config.setProductList(productList);
        tableData.add(product);
    }

}
