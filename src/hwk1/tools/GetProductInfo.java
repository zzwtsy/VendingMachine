package hwk1.tools;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;

public class GetProductInfo {
    private JSONObject contentJson;

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
        this.contentJson = contentJson;
        String[][] data = new String[0][];
        if (contentJson == null) {
            JOptionPane.showMessageDialog(null, "请初始化产品信息");
        } else {
            data = new String[contentJson.length()][4];
            for (int i = 0; i < contentJson.length(); i++) {
                String n = String.valueOf(i);
                String productPrice = (String) contentJson.getJSONObject(n).get("productPrice");
                String productNumbers = (String) contentJson.getJSONObject(n).get("productNumbers");
                String productName = (String) contentJson.getJSONObject(n).get("productName");
                data[i][0] = String.valueOf(i + 1);
                data[i][1] = productName;
                data[i][2] = productPrice;
                data[i][3] = productNumbers;
            }
        }
        return data;
    }

    public JSONObject getContentJson() {
        return contentJson;
    }

    public void setContentJson(JSONObject contentJson) {
        this.contentJson = contentJson;
    }
}
