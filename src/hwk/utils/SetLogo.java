package hwk.utils;

import javax.swing.*;
import java.util.Objects;

public class SetLogo {
    /**
     * 设置程序Logo
     *
     * @param frame JFrame
     */
    public void setIconImage(JFrame frame) {
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/keqing.png"))).getImage());
    }
}
