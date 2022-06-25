package hwk.utils;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;

/**
 * @author meng
 */
public class GetProductInfo {
    private String productPrice;
    private String productName;
    private String productNumbers;

    /**
     *
     * @param n 用户选择的产品序号
     * @param contentJson 产品json数据
     * @return 返回产品价格
     */
    public String getProductPrice(int n, JSONObject contentJson) {
        productPrice = (String) contentJson.getJSONObject(String.valueOf(n)).get("productPrice");
        return productPrice;
    }

    /**
     *
     * @param n 用户选择的产品序号
     * @param contentJson 产品json数据
     * @return 返回产品名称
     */
    public String getProductName(int n, JSONObject contentJson) {
        productName = (String) contentJson.getJSONObject(String.valueOf(n)).get("productName");
        return productName;
    }

    /**
     *
     * @param n 用户选择的产品序号
     * @param contentJson 产品json数据
     * @return 返回产品数量
     */
    public String getProductNumbers(int n, JSONObject contentJson) {
        productNumbers = (String) contentJson.getJSONObject(String.valueOf(n)).get("productNumbers");
        return productNumbers;
    }

    /**
     * 获取json文件中的json数据
     *
     * @return 返回json数据，或null
     */
    public JSONObject getProductInfoJson() {
        try {
            File file = new File("product.json");
            return (JSONObject) MyJson.readJson(file);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取json中的产品信息
     *
     * @param contentJson 保存从getProductInfoJson方法中获取的产品的json数据
     * @return 返回产品信息的二维数组
     */
    public String[][] getProductInfoData(JSONObject contentJson) {
        String[][] data = new String[0][];
        if (contentJson == null) {
            JOptionPane.showMessageDialog(null, "请初始化产品信息");
        } else {
            data = new String[contentJson.length()][4];
            for (int i = 0; i < contentJson.length(); i++) {
                String n = String.valueOf(i);
                productPrice = (String) contentJson.getJSONObject(n).get("productPrice");
                productNumbers = (String) contentJson.getJSONObject(n).get("productNumbers");
                productName = (String) contentJson.getJSONObject(n).get("productName");
                data[i][0] = String.valueOf(i + 1);
                data[i][1] = productName;
                data[i][2] = productPrice;
                data[i][3] = productNumbers;
            }
        }
        return data;
    }
}
