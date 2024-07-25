/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;

/**
 *
 * @author Mostafa Hussein
 */
public class placeholderTextField extends JTextField{
    
            //initializing Variables
            private String placeholder;

            //Constructor
            public placeholderTextField(String placeholder, Color originalFG, Color selectedFG, Border originalBorder, Border selectedBorder) {
                
                this.placeholder = placeholder;
                setText(placeholder); //Setting placeholder
                addFocusListener(new FocusListener() {
                    
                    //When Selected and Empty, Setting Placeholder to nothing
                    @Override
                    public void focusGained(FocusEvent e) {
                        if (getText().equals(placeholder)) {
                            setText("");
                            setForeground(selectedFG); // Selected Foreground
                            setBorder(selectedBorder); // Selected Border
                           
                        }
                    }
                    
                    //When Not Selected and Empty, Setting Placeholder to some Text
                    @Override
                    public void focusLost(FocusEvent e) {
                        if (getText().isEmpty()) {
                            setText(placeholder);
                            setForeground(originalFG); // Orignal Foreground
                            setBorder(originalBorder); // Orignal Border
                        }
                    }
                });
                
            }
}
