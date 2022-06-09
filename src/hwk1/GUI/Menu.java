package hwk1.GUI;

import javax.swing.*;

public class Menu {
    private JFrame frame;
    private JPanel root;
    private JButton maintainSystemButton;
    private JButton sellSystemButton;
    private JButton initSystemButton;
    private JButton exitSystemButton;

    public Menu() {
        exitSystemButton.addActionListener(e -> System.exit(0));
    }

    public void menuRun() {
        frame = new JFrame("Menu");
        frame.setContentPane(this.root);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
