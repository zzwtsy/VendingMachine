package hwk;

import com.formdev.flatlaf.FlatDarculaLaf;
import hwk.gui.Register;
import hwk.gui.login.Login;
import hwk.utils.CheckRegistered;

import javax.swing.*;
import java.io.File;

/**
 * @author zzwtsy
 */
public class VendingMachine {
    static String userNameJson;
    static String userPwdJson;

    public static void main(String[] args) {
        //MacOS窗口标题栏自动更改颜色
        System.setProperty("apple.awt.application.appearance", "system");
        //引入FlatDarculaLaf主题
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Register register = new Register();
        CheckRegistered checkRegistered = new CheckRegistered();
        File jsonFile = new File("config.json");
        //检查config.json文件是否存在
        if (jsonFile.exists()) {
            //判断管理员是否注册
            if (checkRegistered.checkRegistered()) {
                userNameJson = checkRegistered.getUserNameJson();
                userPwdJson = checkRegistered.getUserPwdJson();
                new Login().loginRun(userNameJson, userPwdJson);
            } else {
                register.registerRun();
            }
        } else {
            register.registerRun();
        }
    }
}
