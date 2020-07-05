package kr.hmit.afbis.ui.drawer_menu;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuVO implements Serializable {
    private static final long serialVersionUID = 3259562036597890454L;

    public String MenuTitle;
    public ArrayList<String> SubMenu;
    public boolean Open;

    public MenuVO(String menuTitle, ArrayList<String> subMenu) {
        MenuTitle = menuTitle;
        SubMenu = subMenu;
    }
}
