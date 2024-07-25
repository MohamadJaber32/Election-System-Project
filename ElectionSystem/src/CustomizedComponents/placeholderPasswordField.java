package CustomizedComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 *
 * @author Mostafa Hussein
 */
public class placeholderPasswordField extends JPasswordField{
    
            //Initializing Variables
            private String placeholder;

            //Constructor
            public placeholderPasswordField(String placeholder, Color originalFG, Color selectedFG, Border originalBorder, Border selectedBorder) {
                this.placeholder = placeholder;
                setEchoChar((char) 0);
                setText(placeholder);
                addFocusListener(new FocusListener() {
                    
                    //When Selected and Empty, Setting Placeholder to nothing
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (getText().equals(placeholder)) {
                            setText("");
                            setEchoChar('*'); //Hiding Text
                            setForeground(selectedFG); // Selected Foreground
                            setBorder(selectedBorder); // Selected Border
                        }
                    }
                    
                    //When Not Selected and Empty, Setting Placeholder to some Text
                    @Override
                    public void focusLost(FocusEvent e) {
                        if (getText().isEmpty()) {
                            setEchoChar((char) 0);
                            setText(placeholder);
                            setForeground(originalFG); // Orignal Foreground
                            setBorder(originalBorder); // Orignal Border
                        }
                    }
                });
                
            }
}
