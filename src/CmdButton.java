
import javax.swing.JButton;

/**
 * button that is a command
 */
public class CmdButton extends JButton {

    /**
     * all the option of commands
     */
    public enum COMMAND {
        MOVE_FWD,
        MOVE_BWD,
        MOVE_RIGHT,
        MOVE_LEFT,
        STEER_RIGHT,
        STEER_LEFT
    }

    /**
     * type of button
     */
    COMMAND type;

    /**
     * creates new button
     *
     * @param type the command the button relates to
     */
    CmdButton(COMMAND type) {
        this.type = type;
        setSize(100, 100);
        setVisible(true);

        /* put the text of the button in hebraw bcz kids are dum dum */
        switch (type) {
            case MOVE_FWD:
                setText("תלך קדימה");
                break;
            case MOVE_BWD:
                setText("תלך אחורה");
                break;
            case MOVE_LEFT:
                setText("תלך שמאלה");
                break;
            case MOVE_RIGHT:
                setText("תלך ימינה");
                break;
            case STEER_LEFT:
                setText("תסתובב שמאלה");
                break;
            case STEER_RIGHT:
                setText("תסתובב ימינה");
                break;
        }
    }
}
