
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * the nav bar of the commands
 */
public class CmdNavBar extends JPanel {

    /**
     * sets the buttons in the nav bar
     *
     * @param buttons the buttons that will be in the nav bar
     */
    CmdNavBar(CmdButton... buttons) {
        setVisible(true);
        setLayout(new GridLayout(1, buttons.length, 25, 0));
        setBounds(50, 850, 825, 100);

        /* puts the buttons */
        for (CmdButton button : buttons) {
            add(button);
        }

    }

}
