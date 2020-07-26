package kr.hmit.afbis.model.response;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginModel extends BaseModel implements Serializable {
    private static final long serialVersionUID = -8740195596145373346L;

    public ArrayList<UserInfo> Data;

    public class UserInfo {
        public String MEM_CID;
        public String MEM_01;
        public String MEM_02;
        public String MEM_51;
        public String TKN_03;
        public String MEM_99;
        public boolean Validation;
        public String ErrorCode;
    }
}
