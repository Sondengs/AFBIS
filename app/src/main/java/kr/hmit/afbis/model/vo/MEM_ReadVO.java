package kr.hmit.afbis.model.vo;

import java.io.Serializable;

public class MEM_ReadVO implements Serializable {
    private static final long serialVersionUID = 8591859710640260416L;

    public String MEM_CID;
    public String MEM_01;
    public String MEM_02;
    public String MEM_32;
    public String MEM_32_NM;

    public boolean Validation;
    public String ErrorCode;
}
