import com.formdev.flatlaf.FlatLightLaf;
import data.Config;
import data.Product;
import ui.Setting;
import util.Const;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Config.initConfig();
        Const.config.setProductList(List.of(new Product().setProductCode("1").setProductName("可乐").setPrice(3).setQuantity(10)));
        Config.save();
        // 设置外观
        FlatLightLaf.setup();
        Setting.showSetting();
//        Login.showLogin(Vending::showVendingWindow);
    }
}