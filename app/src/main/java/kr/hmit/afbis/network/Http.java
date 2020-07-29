package kr.hmit.afbis.network;

import kr.hmit.afbis.model.response.MEM_ReadModel;
import kr.hmit.afbis.model.response.WKS_Model;
import kr.hmit.base.model.BaseModel;
import kr.hmit.base.network.HttpBaseService;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class Http extends HttpBaseService {
    private static final String WKS_URL = "Z02001C001";

    //--------------------------------------------------
    // 업무 관리
    //--------------------------------------------------
    public static WKS wks(TYPE type, String host) {
        return (WKS) retrofit(WKS.class, type, host);
    }

    public interface WKS {
        /**
         * 사용자 리스트
         */
        @GET("{host}/" + WKS_URL + "/MEM_Read")
        Call<MEM_ReadModel> MEM_Read(
                @Path(value = "host", encoded = true) String host,
                @Query("MEM_CID") String MEM_CID,
                @Query("MEM_01") String MEM_01,
                @Query("TKN_03") String TKN_03
        );

        /**
         * 업무분류 리스트
         */
        @GET("{host}/" + WKS_URL + "/WKS_05")
        Call<WKS_Model> WKS_05(
                @Path(value = "host", encoded = true) String host,
                @Query("MEM_CID") String MEM_CID,
                @Query("MEM_01") String MEM_01,
                @Query("TKN_03") String TKN_03,
                @Query("WKS_ID") String WKS_ID
        );

        /**
         * 업무관리 리스트
         */
        @GET("{host}/" + WKS_URL + "/WKS_Read")
        Call<WKS_Model> WKS_Read(
                @Path(value = "host", encoded = true) String host,
                @Query("MEM_CID") String MEM_CID,
                @Query("MEM_01") String MEM_01,
                @Query("TKN_03") String TKN_03,
                @Query("GUBUN") String GUBUN,
                @Query("WKS_ID") String WKS_ID,
                @Query("WKS_98") String WKS_98
        );

        /**
         * 업무관리 상세
         */
        @GET("{host}/" + WKS_URL + "/WKS_DETAIL")
        Call<WKS_Model> WKS_DETAIL(
                @Path(value = "host", encoded = true) String host,
                @Query("MEM_CID") String MEM_CID,
                @Query("MEM_01") String MEM_01,
                @Query("TKN_03") String TKN_03,
                @Query("WKS_ID") String WKS_ID,
                @Query("WKS_01") String WKS_01
        );

        /**
         * 업무관리 신규,수정,삭제
         */
        @FormUrlEncoded
        @POST("{host}/" + WKS_URL + "/WKS_U")
        Call<BaseModel> WKS_U(
                @Path(value = "host", encoded = true) String host,
                @Query("MEM_CID") String MEM_CID,
                @Query("MEM_01") String MEM_01,
                @Query("TKN_03") String TKN_03,
                @Query("GUBUN") String GUBUN,
                @Query("WKS_ID") String WKS_ID,
                @Query("WKS_01") int WKS_01,
                @Query("WKS_02") String WKS_02,
                @Query("WKS_03") String WKS_03,
                @Query("WKS_04") String WKS_04,
                @Query("WKS_05") String WKS_05,
                @Query("WKS_06") String WKS_06,
                @Query("WKS_07") String WKS_07,
                @Query("WKS_1001") String WKS_1001,
                @Query("WKS_1002") String WKS_1002,
                @Query("WKS_1101") String WKS_1101,
                @Query("WKS_1102") String WKS_1102,
                @Query("WKS_1201") String WKS_1201,
                @Query("WKS_1202") String WKS_1202,
                @Query("WKS_1301") String WKS_1301,
                @Query("WKS_1302") String WKS_1302,
                @Query("WKS_1401") String WKS_1401,
                @Query("WKS_1402") String WKS_1402,
                @Query("WKS_7001") String WKS_7001,
                @Query("WKS_7002") int WKS_7002,
                @Query("WKS_7003") String WKS_7003,
                @Query("WKS_97") String WKS_97,
                @Query("WKS_98") String WKS_98,
                @Query("WKS_99") String WKS_99
        );
    }
}