/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedComponents;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Mostafa Hussein
 */
public class roundedButton extends JButton{
    
            //Initialize variables
            private final int radius;
            private final Color backgroundColor;
            private final Color pressedColor;

            //Constructor
            public roundedButton(String text, int radius, Color backgroundColor, Color pressedColor) {
                super(text);
                this.radius = radius;
                this.backgroundColor = backgroundColor;
                this.pressedColor = pressedColor;
                setContentAreaFilled(false); // Allow custom painting
                setFocusPainted(false); // No focus outline
                setOpaque(false); // Ensure background is painted
                
                
                //Change Color when selected
                addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        repaint();
                    }
                });
            }
            
            //Main paint Method
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                
                //Setting Color to Gray When Pressed, and Dark Blue When Not
                Color bgColor = getModel().isArmed() ? pressedColor : backgroundColor;
                
                // Fill the rounded background withh color
                g2d.setColor(bgColor);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);

                // Draw the button text and other components
                super.paintComponent(g);

                g2d.dispose();
            }
            
            //paint Border Method
            @Override
            protected void paintBorder(Graphics g) {
                
                // Optionally, draw a border around the button if needed
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                //Setting Border Color to Gray When Pressed, and Dark Blue When Not
                Color bgColor = getModel().isArmed() ? pressedColor : backgroundColor;
                
                g2d.setColor(bgColor); // Border color
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2d.dispose();
            }
}
