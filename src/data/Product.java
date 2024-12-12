package data;

import java.util.Objects;

/**
 * 销售产品列表
 */
public class Product {
    private String productCode;
    private String productName;
    private double price;
    private int quantity;

    /**
     * 产品价格
     *
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置产品价格
     *
     * @param price double
     * @return ProductList
     */
    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * 获取产品编号
     *
     * @return String
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编号
     *
     * @param productCode 产品编号
     * @return ProductList
     */
    public Product setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    /**
     * 获取产品名称
     *
     * @return String
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     * @return ProductList
     */
    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    /**
     * 获取产品数量
     *
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 设置产品数量
     *
     * @param quantity 产品数量
     * @return ProductList
     */
    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return Double.compare(price, that.price) == 0 && quantity == that.quantity && Objects.equals(productCode, that.productCode) && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, productName, price, quantity);
    }
}
