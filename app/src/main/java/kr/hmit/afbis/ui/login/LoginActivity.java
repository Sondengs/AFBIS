
package kr.hmit.afbis.ui.login;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityLoginBinding;
import kr.hmit.afbis.http.BaseConst;
import kr.hmit.afbis.http.Http;
import kr.hmit.afbis.model.LoginModel;
import kr.hmit.afbis.model.request.RequestLogin;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.base_alret.BaseAlert;
import kr.hmit.base.network.ClsNetworkCheck;
import kr.hmit.base.network.HttpBaseService;
import kr.hmit.base.util.PermissionUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    //=========================
    // final
    //=========================


    //=========================
    // view
    //=========================
    private ActivityLoginBinding binding;


    //=========================
    // variable
    //=========================


    //=========================
    // initialize
    //=========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        binding.btnLogin.setOnClickListener(v -> checkValidation());
    }

    @Override
    protected void initialize() {
        if (BuildConfig.DEBUG) {
            binding.etCode.setText("HUMAN");
            binding.etID.setText("dyjung");
            binding.etPW.setText("fose1245");
        }
    }

    /**
     * 값 입력 여부 확인
     */
    private void checkValidation() {
        String strCode = binding.etCode.getText().toString().trim();
        String strID = binding.etID.getText().toString().trim();
        String strPassword = binding.etPW.getText().toString().trim();

        if (TextUtils.isEmpty(strCode)) {
            BaseAlert.show(mContext, R.string.login_0);
            return;
        }

        if (TextUtils.isEmpty(strID)) {
            BaseAlert.show(mContext, R.string.login_1);
            return;
        }

        if (TextUtils.isEmpty(strPassword)) {
            BaseAlert.show(mContext, R.string.login_2);
            return;
        }

        RequestLogin param = new RequestLogin();
        param.GUBUN = "";
        param.MEM_CID = strCode;
        param.MEM_01 = strID;
        param.MEM_03 = strPassword;

        requestLogin(param);
    }


    //=========================
    // api
    //=========================

    /**
     * 로그인을 한다.
     */
    private void requestLogin(RequestLogin param) {
        if (!ClsNetworkCheck.isConnectable(mContext)) {
            BaseAlert.show(mContext, R.string.network_error_1);
            return;
        }

        openLoadingBar();

        Http.member(HttpBaseService.TYPE.POST, BaseConst.URL_HOST).login(
                BaseConst.URL_HOST,
                param
        ).enqueue(new Callback<LoginModel>() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Message msg = new Message();
                msg.obj = response;
                msg.what = 100;

                //=====================
                // response callback
                //=====================
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 100) {
                            closeLoadingBar();

                            Response<LoginModel> response = (Response<LoginModel>) msg.obj;

                            if (response.isSuccessful()) {
                                if (response.body().Data.get(0).Validation) {
                                    toast("로그인 성공");
                                } else {
                                    BaseAlert.show(mContext, "ErrorCode : " + response.body().Data.get(0).ErrorCode);
                                }
                            } else {
                                BaseAlert.show(mContext, R.string.network_error_2);
                            }
                        }
                    }
                }.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                closeLoadingBar();
                call.cancel();
            }
        });
    }


    //=========================
    // event
    //=========================

}
