/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedComponents;

import java.awt.*;
import javax.swing.border.Border;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author Mostafa Hussein
 */
public class roundedBorder implements Border{
    
            //Initialize Variables
            private int radius;
            private Color color;
            
            //Constructor
            public roundedBorder(int radius, Color color) {
                this.radius = radius;
                this.color = color;
            }

            //Setting the Margins
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }
            
            //Main Method to Draw Rounded Border
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g.create(); //Initialize graphics
                g2d.setColor(this.color); //Setting colors
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius)); //Drawing the rounded Border
                g2d.dispose();
            }
}
