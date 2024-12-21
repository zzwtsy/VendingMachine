import com.formdev.flatlaf.FlatLightLaf;
import data.Config;
import data.Log;
import ui.Login;
import ui.Setting;


public class Main {
    public static void main(String[] args) {
        Log.initLog();
        Config.initConfig();
        Config.save();
        // 设置外观
        FlatLightLaf.setup();
        Login.showLogin(Setting::showSetting);
    }
}