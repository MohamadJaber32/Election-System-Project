/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CustomizedComponents;

import MainFrame.LoginPage;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class loadCustomFonts {
    public loadCustomFonts(String fontPath){
        
        try (InputStream is = LoginPage.class.getResourceAsStream(fontPath)) {
            // Load the font from the resource stream
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            // Register the font with the graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
