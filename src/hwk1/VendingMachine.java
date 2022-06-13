package hwk1;

import hwk1.GUI.Login;
import hwk1.GUI.Register;
import hwk1.tools.MyJson;
import org.json.JSONObject;

import java.io.File;

/**
 * @author zzwtsy
 */
public class VendingMachine {
    static String userNameJson;
    static String userPwdJson;
    public static void main(String[] args) {
        Register register = new Register();
        File jsonFile = new File("config.json");
        //检查config.json文件是否存在
        if (jsonFile.exists()) {
            //判断管理员是否注册
            if (new VendingMachine().checkRegistered()) {
                new Login().loginRun(userNameJson,userPwdJson);
            } else {
                register.registerRun();
            }
        } else {
            register.registerRun();
        }
    }

    /**
     * 判断管理员是否注册
     */
    public boolean checkRegistered() {
        File file = new File("config.json");
        JSONObject contentJson = (JSONObject) MyJson.readJson(file);
        userNameJson = (String) contentJson.get("userName");
        userPwdJson = (String) contentJson.get("userPwd");
        return userNameJson != null && userPwdJson != null;
    }
}