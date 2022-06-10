package hwk1.GUI;

import hwk1.MyJson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class Initialize {
    private JFrame frame;
    private JPanel root;
    private JTextField initTextField;
    private JButton saveButton;
    private JButton goBackButton;

    public Initialize() {
        saveButton.addActionListener(e -> initData());
        goBackButton.addActionListener(e -> {
            frame.dispose();
            Menu menu = new Menu();
            menu.menuRun();
        });
    }

    public static void main(String[] args) {
        Initialize initialize = new Initialize();
        initialize.initializeRun();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        JSONArray jsonArray = new JSONArray();
        String init = initTextField.getText();
        String[] data = init.split("\\|");
        if (!init.trim().equals("")) {
            for (String datum : data) {
                //jsonObject = null 不可删除
                @SuppressWarnings("all") JSONObject jsonObject = null;
                jsonObject = new JSONObject();
                //detail = null 不可删除
                @SuppressWarnings("all") String[] detail = null;
                detail = datum.split(":");
                jsonObject.put(detail[0] + "的名称", detail[0]);
                jsonObject.put(detail[0] + "的价格", detail[1]);
                jsonObject.put(detail[0] + "的数量", detail[2]);
                jsonArray.put(jsonObject);
            }
            File jsonFile = new File("product.json");
            try {
                MyJson.writeJson(jsonArray, jsonFile);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "保存失败");
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "保存成功");
            initTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "保存内容不能为空");
        }
    }

    public void initializeRun() {
        frame = new JFrame("Initialize");
        frame.setContentPane(this.root);
        frame.pack();
        frame.setVisible(true);
        //点击X时返回上一级窗口
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                onCancel();
                frame.dispose();
                Menu menu = new Menu();
                menu.menuRun();
            }
        });
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
