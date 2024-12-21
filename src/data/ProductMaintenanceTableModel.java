package data;


import util.Const;
import util.JsonUtil;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        // 将rowIndex数组中的元素转换为ProductCode，并存储到productCodeList列表中
        List<String> productCodeList = Arrays.stream(rowIndex)
                .mapToObj(index ->
                        // 获取tableData中index位置的元素，并获取其ProductCode
                        super.tableData.get(index).getProductCode()
                ).toList();

        // 获取产品列表
        ArrayList<Product> productList = new ArrayList<>(Const.config.getProductList());
        // 创建迭代器
        Iterator<Product> iterator = productList.iterator();
        int count = 0;
        // 遍历产品代码列表
        while (iterator.hasNext()) {
            // 获取产品
            Product product = iterator.next();
            // 如果产品代码不匹配跳过
            if (!productCodeList.contains(product.getProductCode())) {
                continue;
            }
            // 从产品列表中移除产品
            iterator.remove();
            // 从表格数据中移除产品
            tableData.remove(product);
            // 记录日志
            Log log = new Log("删除商品", ZonedDateTime.now().toString(), Const.config.getUserName(), JsonUtil.encodeToString(product));
            Log.save(log);
            // 计数
            count++;
            // 如果计数等于产品代码列表大小，跳出循环
            if (count == productCodeList.size()) break;
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
        // 创建一个ArrayList，并将Const.config.getProductList()中的元素添加到其中
        ArrayList<Product> productList = new ArrayList<>(Const.config.getProductList());
        // 将传入的商品添加到ArrayList中
        productList.add(product);
        // 将修改后的ArrayList设置到Const.config中
        Const.config.setProductList(productList);
        // 将商品添加到tableData中
        tableData.add(product);
    }

}
