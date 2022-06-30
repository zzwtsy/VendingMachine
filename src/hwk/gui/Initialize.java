package hwk.gui;

import hwk.utils.SetLogo;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.SaveProductData.saveProductData;
import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class Initialize {
    private JFrame frame;
    private JPanel root;
    private JTextField initTextField;
    private JButton saveButton;
    private JButton goBackButton;
    private JLabel textHeader;
    private JLabel textTip;
    private JLabel textSample;

    /**
     * Initialize 监听事件
     */
    public Initialize() {
        saveButton.addActionListener(e -> {
            if (saveProductData(initTextField.getText()) == 1) {
                JOptionPane.showMessageDialog(null, "保存成功");
                initTextField.setText("");
            } else if (saveProductData(initTextField.getText()) == -1) {
                JOptionPane.showMessageDialog(null, "保存失败");
                initTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "保存内容不能为空");
            }
        });
        goBackButton.addActionListener(e -> {
            frame.dispose();
            new Menu().menuRun();
        });
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
     * Initialize 入口
     */
    public void initializeRun() {
        frame = new JFrame("Initialize");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        //点击X时返回上一级窗口
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new Menu().menuRun();
            }
        });
        //窗口居中
        initFrame(frame);
    }
}
