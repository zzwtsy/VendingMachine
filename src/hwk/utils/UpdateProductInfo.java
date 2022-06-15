package hwk.utils;

import org.json.JSONObject;

import java.io.File;

public class UpdateProductInfo {
    /**
     *
     * @param n 用户选择的商品序号
     * @param j 用户选择的商品数量
     */
    public static void updateProductInfo(int n, int j) {
        GetProductInfo getProductInfo = new GetProductInfo();
        String[][] data = getProductInfo.getProductInfoData(getProductInfo.getProductInfoJson());
        JSONObject json = new JSONObject();
        for (int i = 0; i < data.length; i++) {
            //jsonObject = null 不可删除
            @SuppressWarnings("all") JSONObject jsonObject = null;
            jsonObject = new JSONObject();
            //detail = null 不可删除
            @SuppressWarnings("all") String[] detail = null;
            jsonObject.put("productName", data[i][1]);
            jsonObject.put("productPrice", data[i][2]);
            //如果i=n 商品数量减一
            if (n == i) {
                jsonObject.put("productNumbers", String.valueOf(Integer.parseInt(data[i][3]) - j));
            } else {
                jsonObject.put("productNumbers", data[i][3]);
            }
            json.put(String.valueOf(i), jsonObject);
        }
        File jsonFile = new File("product.json");
        try {
            MyJson.writeJson(json, jsonFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
