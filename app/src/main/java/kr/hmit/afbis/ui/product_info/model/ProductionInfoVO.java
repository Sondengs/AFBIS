package kr.hmit.afbis.ui.product_info.model;

import java.io.Serializable;

public class ProductionInfoVO implements Serializable {
    private static final long serialVersionUID = 229404674872735490L;

    public String DAH_ID;
    /**
     * 바코드
     */
    public String DAH_01;
    /**
     * 제품명
     */
    public String DAH_02;
    /**
     * 자재구분 (R - 원자재, P - 완제품, B - 부자재, S - 반제품)
     */
    public String DAH_03;
    /**
     * 단위
     */
    public String DAH_04;
    /**
     * 중량
     */
    public int DAH_05;
    public String DAH_06;
    public String DAH_07;
    public String DAH_08;
    public String DAH_09;
    public String DAH_10;
    public int DAH_11;
    public String DAH_12;
    public String DAH_13;
    /**
     * 규격
     */
    public String DAH_14;
    public int DAH_15;
    public String DAH_1601;
    public String DAH_1602;
    public int DAH_1603;
    public int DAH_17;
    public int DAH_18;
    public int DAH_19;
    public String DAH_20;
    public String DAH_21;
    public String DAH_80;
    public String DAH_98;
    public String DAH_99;
    public String REQ_03;
    public String CDO_03;
    /**
     * 재고량
     */
    public int OOK_07;
    public boolean Validation;
    public String SUCCESS;
    public String ERROR_MSG;
}
