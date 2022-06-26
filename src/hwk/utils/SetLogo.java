package hwk.utils;

import javax.swing.*;
import java.util.Objects;

public class SetLogo {
    public void setIconImage(JFrame frame){
        frame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/keqing.png"))).getImage());
    }
}
