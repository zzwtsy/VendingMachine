package hwk.utils;

import org.json.JSONObject;

import java.io.File;

/**
 * @author meng
 */
public class CheckRegistered {
    private String userNameJson;
    private String userPwdJson;

    public String getUserNameJson() {
        return userNameJson;
    }

    public String getUserPwdJson() {
        return userPwdJson;
    }

    /**
     * 判断管理员是否注册
     *
     * @return config.json中userName与userPwd不为空返回true否则返回false
     */
    public boolean checkRegistered() {
        File file = new File("config.json");
        JSONObject contentJson = (JSONObject) MyJson.readJson(file);
        userNameJson = (String) contentJson.get("userName");
        userPwdJson = (String) contentJson.get("userPwd");
        return userNameJson != null && userPwdJson != null;
    }
}
