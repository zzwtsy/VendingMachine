package hwk1.GUI;

import com.google.gson.Gson;
import hwk1.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class Register {
    static String userNameJson;
    static String userPwdJson;
    private final JFrame frame = new JFrame("register");
    String registerUserName;
    String registerUserPwd1;
    String registerUserPwd2;
    private JTextField registerName;
    private JPasswordField registerPasswordField1;
    private JPasswordField registerPasswordField2;
    private JPanel root;
    private JButton registerButton;

    public Register() {
        registerButton.addActionListener(e -> {
            registerUserName = registerName.getText();
            registerUserPwd1 = String.valueOf(registerPasswordField1.getPassword());
            registerUserPwd2 = String.valueOf(registerPasswordField2.getPassword());
            //用户点击注册时判断用户名是否为空，trim：删除前导和尾随空格
            if (registerUserName.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "用户名不能为空");
                //用户点击注册时判断密码1是否为空，trim：删除前导和尾随空格
            } else if (registerUserPwd1.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
                //用户点击注册时判断密码2是否为空，trim：删除前导和尾随空格
            } else if (registerUserPwd2.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "密码不能为空");
                //用户点击注册时判断密码1与密码2是否一致，trim：删除前导和尾随空格
            } else if (!registerUserPwd1.equals(registerUserPwd2)) {
                JOptionPane.showMessageDialog(null, "两次密码不一致");
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userName", registerUserName);
                jsonObject.put("userPwd", registerUserPwd1);
                File jsonFile = new File("config.json");
                try {
                    MyJson.writeJson(jsonObject, jsonFile);
                    toLoginFrame(registerUserName, registerUserPwd1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        Register register = new Register();
        File jsonFile = new File("config.json");
        //检查config.json文件是否存在
        if (jsonFile.exists()) {
            //判断管理员是否注册
            if (register.unregistered()) {
                register.toLoginFrame(userNameJson, userPwdJson);
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
    public boolean unregistered() {
        String contentJson = MyJson.readJson("config.json");
        Gson gson = new Gson();
        Map<String, String> mapJson = gson.fromJson(contentJson, Map.class);
        userNameJson = mapJson.get("userName");
        userPwdJson = mapJson.get("userPwd");
        return userNameJson != null && userPwdJson != null;
    }

    /**
     * 跳转登录窗口
     */
    public void toLoginFrame(String userNameJson, String userPwdJson) {
        //关闭注册窗口
        frame.dispose();
        Login login = new Login();
        login.loginRun(userNameJson, userPwdJson);
    }

    public void registerRun() {
        frame.setContentPane(this.root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        // 获得窗口宽
        int windowWidth = frame.getWidth();
        // 获得窗口高
        int windowHeight = frame.getHeight();
        // 定义工具包
        Toolkit kit = Toolkit.getDefaultToolkit();
        // 获取屏幕的尺寸
        Dimension screenSize = kit.getScreenSize();
        // 获取屏幕的宽
        int screenWidth = screenSize.width;
        // 获取屏幕的高
        int screenHeight = screenSize.height;
        // 设置窗口居中显示
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);
    }
}
