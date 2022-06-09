package hwk1.GUI;

import javax.swing.*;

public class Maintain {
    private JPanel root;
    private JButton displayLogButton;
    private JButton productControlButton;
    private JButton modifyUserPwdButton;
    private JButton goBackButton;

    public Maintain() {
        goBackButton.addActionListener(e -> {
            Menu menu =new Menu();
            menu.menuRun();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maintain");
        frame.setContentPane(new Maintain().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
