package hwk.utils;

import org.json.JSONObject;

import java.io.File;

public class CheckRegistered {
    /**
     * 判断管理员是否注册
     *
     * @return config.json中userName与userPwd不为空返回true否则返回false
     */
    public static boolean checkRegistered() {
        File file = new File("config.json");
        JSONObject contentJson = (JSONObject) MyJson.readJson(file);
        String userNameJson = (String) contentJson.get("userName");
        String userPwdJson = (String) contentJson.get("userPwd");
        return userNameJson != null && userPwdJson != null;
    }
}
