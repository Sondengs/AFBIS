package kr.hmit.afbis.model.response;

import java.io.Serializable;
import java.util.ArrayList;

import kr.hmit.afbis.model.vo.MEM_ReadVO;

public class MEM_ReadModel extends BaseModel implements Serializable {
    public ArrayList<MEM_ReadVO> Data;
}
