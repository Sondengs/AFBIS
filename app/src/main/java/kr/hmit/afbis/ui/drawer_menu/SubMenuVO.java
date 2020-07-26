package kr.hmit.afbis.ui.drawer_menu;

import java.io.Serializable;

public class SubMenuVO implements Serializable {
    private static final long serialVersionUID = -7588188816679912148L;

    public String SubTitle;
    public String Code;

    public SubMenuVO(String subTitle, String code) {
        SubTitle = subTitle;
        Code = code;
    }
}