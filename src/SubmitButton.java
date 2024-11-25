
import javax.swing.JButton;

/**
 * submit buttons
 */
public class SubmitButton extends JButton {

    /**
     * all of the submit options
     */
    enum SUBMIT_OPTIONS {
        SUBMIT,
        ERASE,
        CLEAR
    }

    /**
     * creates submit button
     *
     * @param type type of the submit button
     */
    SubmitButton(SUBMIT_OPTIONS type) {
        setSize(100, 100);
        setVisible(true);

        /* set the text of the button based on the type */
        switch (type) {
            case SUBMIT:
                setText("שליחת קוד");
                break;
            case ERASE:
                setText("מחיקת שורה");
                break;
            case CLEAR:
                setText("איפוס");
                break;
        }
    }
}
