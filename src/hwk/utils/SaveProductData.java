package hwk.utils;

import org.json.JSONObject;

import java.io.File;

public class SaveProductData {
    /**
     * 保存初始化数据
     *
     * @param initTextField 初始化数据
     * @return 1：保存成功，-1：保存失败，0：输入数据内容为空
     */
    public static int saveProductData(String initTextField) {
        JSONObject json = new JSONObject();
        String[] data = initTextField.split("\\|");
        if (!initTextField.trim().equals("")) {
            int i = 0;
            for (String datum : data) {
                //jsonObject = null 不可删除
                @SuppressWarnings("all") JSONObject jsonObject = null;
                jsonObject = new JSONObject();
                //detail = null 不可删除
                @SuppressWarnings("all") String[] detail = null;
                detail = datum.split(":");
                jsonObject.put("productName", detail[0]);
                jsonObject.put("productPrice", detail[1]);
                jsonObject.put("productNumbers", detail[2]);
                json.put(String.valueOf(i), jsonObject);
                i += 1;
            }
            File jsonFile = new File("product.json");
            try {
                MyJson.writeJson(json, jsonFile);
            } catch (Exception ex) {
                return -1;
            }
            return 1;
        } else {
            return 0;
        }
    }
}
