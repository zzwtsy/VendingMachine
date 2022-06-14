package hwk;

import hwk.gui.Register;
import hwk.gui.login.Login;

import java.io.File;

import static hwk.utils.CheckRegistered.checkRegistered;

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
            if (checkRegistered()) {
                new Login().loginRun(userNameJson, userPwdJson);
            } else {
                register.registerRun();
            }
        } else {
            register.registerRun();
        }
    }
}