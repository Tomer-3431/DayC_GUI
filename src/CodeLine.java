
import java.awt.Color;
import javax.swing.JTextField;

/**
 * a line of code
 */
public class CodeLine extends JTextField {

    /**
     * a line of code
     *
     * @param text the text that will be on the command
     * @param lineNum
     */
    CodeLine(String text, int lineNum) {
        setVisible(true);
        setForeground(Color.white);
        setBackground(Color.BLACK);
        setText(String.valueOf(lineNum) + ": " + text);
        setBounds(25, ((lineNum - 1) * 50) + 25, 938 - 100, 50);
    }
}
