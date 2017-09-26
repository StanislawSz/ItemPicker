
package mousemove;

import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

/**
 *
 * @author Stas
 */
public class Main implements NativeKeyListener, NativeMouseInputListener
{
    private final TrayClass tray = new TrayClass();
    private final UserPref pref = new UserPref();
    private Point bpPosition = new Point(pref.getPosX(), pref.getPosY());
    private int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        Main main = new Main();
        GlobalScreen.addNativeKeyListener(main);
        GlobalScreen.addNativeMouseListener(main);

    }
    
    
    public static void moveItem(Point source, Point dest) throws AWTException
    {
        Robot bot = new Robot();
        Random generator = new Random();
        
        bot.mouseMove(source.x, source.y);    
        bot.mousePress(InputEvent.BUTTON1_MASK);
//        bot.mouseMove(x2+generator.nextInt(13)-6, y2+generator.nextInt(13)-6);
        bot.mouseMove(dest.x+generator.nextInt(115)-5, dest.y+generator.nextInt(11)-5);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.mouseMove(source.x, source.y);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent ex)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e)
    {
//        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        
        if (e.getKeyCode() == NativeKeyEvent.VC_PAUSE)
        {
            Point p = MouseInfo.getPointerInfo().getLocation();
            bpPosition.x = p.x;
            bpPosition.y = p.y;
            
            pref.setPosX(bpPosition.x);
            pref.setPosY(bpPosition.y);
        }
        
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent ex)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e)
    {
        if (e.getX() > bpPosition.x-20 && e.getY() > bpPosition.y+35 && e.getY() < screenHeight)
        {
            if (e.getButton() == 1)
            {
                try
                {
                    moveItem(new Point(e.getX(),e.getY()), bpPosition);

                } catch (AWTException ex)
                {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nme)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nme)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
