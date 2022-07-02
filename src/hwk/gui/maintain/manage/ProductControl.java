package hwk.gui.maintain.manage;

import hwk.gui.maintain.SystemMaintain;
import hwk.utils.GetProductInfo;
import hwk.utils.SetLogo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static hwk.utils.WindowCenter.initFrame;

/**
 * @author meng
 */
public class ProductControl {
    private JFrame frame;
    private JPanel root;
    @SuppressWarnings("all")
    private JPanel content;
    @SuppressWarnings("all")
    private JScrollPane contentScrollPane;
    private JTable contentTable;
    private JTextField getChoiceSerialField;
    private JButton okButton;
    @SuppressWarnings("all")
    private JPanel ChoicePanel;
    private JLabel choiceSerialField;
    private JButton goBackButton;

    /**
     * ProductControl 监听事件
     */
    public ProductControl() {
        okButton.addActionListener(e -> {
            String productSerial = getChoiceSerialField.getText();
            new ModifyProductInfo().modifyProductInfoRun(productSerial);
            frame.dispose();
        });
        goBackButton.addActionListener(e -> {
            frame.dispose();
            new SystemMaintain().systemMaintainRun();
        });
    }

    /**
     * 设置窗口文字
     */
    private void setWindowText() {
        choiceSerialField.setText("请输入对应序号");
        okButton.setText("确定");
        goBackButton.setText("返回");
    }

    /**
     * ProductControl 入口
     */
    public void productControlRun() {
        frame = new JFrame("ProductControl");
        frame.setContentPane(this.root);
        //设置图标
        new SetLogo().setIconImage(frame);
        String[] name = {"序号", "名称", "价格/元", "数量/瓶"};
        GetProductInfo getProductInfo = new GetProductInfo();
        //通过GetProductInfo方法获取产品信息存入到data中
        String[][] data = getProductInfo.getProductInfoData(getProductInfo.getProductInfoJson());
        contentTable.setModel(new DefaultTableModel(data, name));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                new SystemMaintain().systemMaintainRun();
            }
        });
        //表格不可拖动
        contentTable.getTableHeader().setReorderingAllowed(false);
        //表格文字只读
        contentTable.setEnabled(false);
        //文字居中
        DefaultTableCellRenderer fontCenter = new DefaultTableCellRenderer();
        fontCenter.setHorizontalAlignment(JLabel.CENTER);
        contentTable.setDefaultRenderer(Object.class, fontCenter);
        frame.pack();
        frame.setVisible(true);
        //设置窗口文字
        setWindowText();
        //窗口居中
        initFrame(frame);
    }
}
