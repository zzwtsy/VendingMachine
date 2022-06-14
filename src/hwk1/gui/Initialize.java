package hwk1.gui;

import hwk1.tools.MyJson;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import static hwk1.tools.WindowCenter.initFrame;

public class Initialize {
    private JFrame frame;
    private JPanel root;
    private JTextField initTextField;
    private JButton saveButton;
    private JButton goBackButton;
    private JLabel textHeader;
    private JLabel textTip;
    private JLabel textSample;

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
     * 设置窗口文字
     */
    private void setWindowText() {
        textHeader.setText("系统初始化界面");
        textTip.setText("请输入商品名称、价格、数量：");
        textSample.setText("示例：矿泉水:2:100|可乐:3:200");
        saveButton.setText("保存");
        goBackButton.setText("返回");
    }

    /**
     * 初始化数据
     */
    public void initData() {
        JSONObject json = new JSONObject();
        String init = initTextField.getText();
        String[] data = init.split("\\|");
        if (!init.trim().equals("")) {
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
        //设置窗口文字
        setWindowText();
        //点击X时返回上一级窗口
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                Menu menu = new Menu();
                menu.menuRun();
            }
        });
        //窗口居中
        initFrame(frame);
    }
}
