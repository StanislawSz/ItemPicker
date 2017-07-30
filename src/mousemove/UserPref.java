
package mousemove;

import java.util.prefs.Preferences;

/**
 *
 * @author Stas
 */
public class UserPref
{
    private final Preferences pref;
    
    public UserPref()
    {
        pref = Preferences.userNodeForPackage(UserPref.class);
        
    }
    
    public int getPosX()
    {
        return pref.getInt("posX", 1778);
    }
    
    public int getPosY()
    {
        return pref.getInt("posY", 672);
    }
    
    public void setPosX(int x)
    {
        pref.putInt("posX", x);
    }
    
    public void setPosY(int y)
    {
        pref.putInt("posY", y);
    }
    
}
