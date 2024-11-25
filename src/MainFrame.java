
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * the main frame of the gui
 */
public class MainFrame extends JFrame implements ActionListener {

    /**
     * all the commands buttons
     */
    CmdButton[] buttons = {
        new CmdButton(CmdButton.COMMAND.MOVE_FWD),
        new CmdButton(CmdButton.COMMAND.MOVE_BWD),
        new CmdButton(CmdButton.COMMAND.MOVE_LEFT),
        new CmdButton(CmdButton.COMMAND.MOVE_RIGHT),
        new CmdButton(CmdButton.COMMAND.STEER_LEFT),
        new CmdButton(CmdButton.COMMAND.STEER_RIGHT)
    };

    /**
     * set up the nav bar
     */
    CmdNavBar commandPanel;

    /**
     * set up the vars that help move code to robot code
     */
    int lines = 0;
    List<CmdButton.COMMAND> code = new ArrayList<>();
    List<CodeLine> panelArr = new ArrayList<>();

    /**
     * set up the panel for the submit buttons
     */
    SubmitButton submit = new SubmitButton(SubmitButton.SUBMIT_OPTIONS.SUBMIT);
    SubmitButton erase = new SubmitButton(SubmitButton.SUBMIT_OPTIONS.ERASE);
    SubmitButton clear = new SubmitButton(SubmitButton.SUBMIT_OPTIONS.CLEAR);
    JPanel submitPanel = new JPanel();

    /**
     * the directory and file of the data
     */
    String directory;
    FileWriter file;

    /**
     * set up the main frame of the gui
     *
     * @param directory the directory of the file
     */
    MainFrame(String directory) {
        /* set up local vars */
        this.directory = directory;

        /* set up the frame pera */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(938, 1000);
        setIconImage(Toolkit.getDefaultToolkit().getImage(".\\assets\\demacia2022.png"));
        setTitle("Day C Programing GUI OOGA BOOGA ONLY ONE NIGHTER LETS FUCKING GOOOOOO");
        setVisible(true);
        getContentPane().setBackground(new Color(64, 20, 89));

        /* puts all the buttons in the nav bar */
        for (CmdButton button : buttons) {
            button.addActionListener(this);
        }
        commandPanel = new CmdNavBar(buttons);
        commandPanel.setBackground(new Color(64, 20, 89));
        add(commandPanel);

        /* puts all the submit buttons in the submit panel */
        submitPanel.add(submit);
        submitPanel.add(erase);
        submitPanel.add(clear);
        submit.addActionListener(this);
        erase.addActionListener(this);
        clear.addActionListener(this);

        /* set up the submit panel */
        submitPanel.setLayout(new GridLayout(1, 3, 50, 0));
        submitPanel.setBounds(250, 725, 400, 100);
        submitPanel.setBackground(new Color(64, 20, 89));
        add(submitPanel);
    }

    /**
     * check for all the button press
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        /* if the buttons is one of the command button */
        for (CmdButton button : buttons) {
            if (actionEvent.getSource() == button) {
                if (lines < 13) {
                    String text;
                    switch (button.type) {
                        case MOVE_FWD:
                            text = "תלך מטר קדימה";
                            break;
                        case MOVE_BWD:
                            text = "תלך מטר אחורה";
                            break;
                        case MOVE_RIGHT:
                            text = "תלך מטר ימינה";
                            break;
                        case MOVE_LEFT:
                            text = "תלך מטר שמאלה";
                            break;
                        case STEER_RIGHT:
                            text = "תסתובב ימינה";
                            break;
                        case STEER_LEFT:
                            text = "תסתובב שמאלה";
                            break;
                        default:
                            text = "error";
                    }
                    panelArr.add(new CodeLine(text, ++lines));
                    add(panelArr.get(panelArr.size() - 1));
                    code.add(button.type);
                }
            }
        }

        /* if the button is erase button */
        if (actionEvent.getSource() == erase) {
            panelArr.get(panelArr.size() - 1).setVisible(false);
            panelArr.remove(panelArr.size() - 1);
            code.remove(code.size() - 1);
            lines--;
        }

        /* if the button is the clear button */
        if (actionEvent.getSource() == clear) {
            for (CodeLine line : panelArr) {
                line.setVisible(false);
            }

            lines = 0;
            code = new ArrayList<>();
            panelArr = new ArrayList<>();
        }

        /* if the button is the submit button */
        if (actionEvent.getSource() == submit) {
            System.out.println(code.toString());
            try {
                file = new FileWriter(directory, false);
                file.write(String.format("""
                /*FILE IS AUTO-GENERATED*/
                \npackage frc.robot.dayC;
                \nimport static frc.robot.dayC.Data.COMMAND.*;
                \npublic class Data {
                \n  public enum COMMAND {MOVE_FWD,MOVE_BWD,MOVE_RIGHT,MOVE_LEFT,STEER_RIGHT,STEER_LEFT}
                \n  public static final COMMAND[] COMMANDS_ARR = {%s};
                \n}
                \n""", 
                code.toString().substring(1, code.toString().length() - 1)));
                file.close();
            } catch (IOException e1) {
                throw new RuntimeException(e1);
            }
        }
    }
}
