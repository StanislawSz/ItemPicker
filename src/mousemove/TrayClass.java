
package mousemove;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Stas
 */
public class TrayClass
{
    static TrayIcon trayIcon;
    
    public TrayClass()
    {
        show();
    }
    
    public static void show()
    {
        if (!SystemTray.isSupported())
        {
            System.exit(0);
        }
        
        trayIcon = new TrayIcon(createIcon("circle.png", "Icon"));
        trayIcon.setToolTip("ItemPicker by Stas");
        trayIcon.setImageAutoSize(true);
        
        final SystemTray tray = SystemTray.getSystemTray();
        final PopupMenu menu = new PopupMenu();
        
        MenuItem exit = new MenuItem("Zamknij");
        MenuItem info = new MenuItem("Informacje");
        
        menu.add(info);
        menu.addSeparator();
        menu.add(exit);
        
        trayIcon.setPopupMenu(menu);
        
        try{
            tray.add(trayIcon);
            
        }catch (AWTException ex){
            Logger.getLogger(TrayClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        info.addActionListener((ActionEvent e) ->
        {
            JOptionPane.showMessageDialog(null, "Pause - zapis kratki");
        });
        
        exit.addActionListener((ActionEvent e) ->
        {
            tray.remove(trayIcon);
            System.exit(0);
        });
        
    }
    
    protected static Image createIcon(String path, String desc)
    {
        URL imageURL = TrayClass.class.getResource(path);
        return (new ImageIcon(imageURL, desc)).getImage();
    }
}
