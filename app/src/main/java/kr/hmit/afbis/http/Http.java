package kr.hmit.afbis.http;

import kr.hmit.afbis.model.LoginModel;
import kr.hmit.afbis.model.request.RequestLogin;
import kr.hmit.base.network.HttpBaseService;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


public class Http extends HttpBaseService {


    //--------------------------------------------------
    // 회원
    //--------------------------------------------------
    public static MEM_SELECT member(TYPE type, String host) {
        return (MEM_SELECT) retrofit(MEM_SELECT.class, type, host);
    }

    public interface MEM_SELECT {

        /**
         * 로그인
         */
        @POST("{host}/Mobile/MEM_SELECT")
        Call<LoginModel> login(
                @Path(value = "host", encoded = true) String host,
                @Body RequestLogin param);
    }
}