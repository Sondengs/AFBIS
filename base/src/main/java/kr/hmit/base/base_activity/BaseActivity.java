package kr.hmit.base.base_activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.hmit.base.BaseApplication;
import kr.hmit.base.R;
import kr.hmit.base.base_alret.BaseAlert;
import kr.hmit.base.model.LoginModel;
import kr.hmit.base.network.BaseConst;
import kr.hmit.base.network.ClsNetworkCheck;
import kr.hmit.base.network.Http;
import kr.hmit.base.network.HttpBaseService;
import kr.hmit.base.settings.InterfaceSettings;
import kr.hmit.base.settings.SettingsKey;
import kr.hmit.base.user_interface.InterfaceUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseActivity extends AppCompatActivity {
    public static Context BaseContext;
    protected Context mContext;
    protected Activity mActivity;
    protected InterfaceSettings mSettings;
    protected InterfaceUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        init();
    }

    /**
     * 초기화
     */
    private void init() {
        if (BaseContext == null)
            BaseContext = this;
        if (mContext == null)
            mContext = this;
        if (mActivity == null)
            mActivity = this;
        if (mSettings == null)
            mSettings = InterfaceSettings.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        init();
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
    }

    /**
     * 레이아웃 초기화
     */
    protected abstract void initLayout();

    /**
     * 데이터 초기화
     */
    protected abstract void initialize();

    protected void openLoadingBar() {
        BaseApplication.getInstance().openLoading(this, null);
    }

    protected void closeLoadingBar() {
        BaseApplication.getInstance().closeLoading();
    }


    @Override
    protected void onPause() {
        super.onPause();

//        if (mLoadingBar != null) {
//            mLoadingBar.dismiss();
//        }
    }

    /**
     * 액티비티 이동
     *
     * @param cls
     */
    protected void goActivity(Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mContext.startActivity(intent);
    }

    protected void goActivity(Class<?> cls, Intent intent) {
        intent.setClass(mContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mContext.startActivity(intent);
    }

    protected void goActivity(Class<?> cls, int requestCode) {
        Intent intent = new Intent(mContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        mActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 토스트 메시지
     *
     * @param message
     */
    protected void toast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    //==============================
    // api
    //==============================
    public interface OnRequestLogin {
        void isSuccess(LoginModel.UserInfo userInfo);

        void isFail(String errorMsg);

        void isNotConnectable();
    }

    protected void requestLogin(final OnRequestLogin onRequestLoginListener) {
        if (!ClsNetworkCheck.isConnectable(mContext)) {
            onRequestLoginListener.isNotConnectable();
            return;
        }

        openLoadingBar();

        Http.member(HttpBaseService.TYPE.POST, BaseConst.URL_HOST).login(
                BaseConst.URL_HOST,
                "",
                "HUMAN",
                "dyjung",
                "fose1245"
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
                                    LoginModel.UserInfo userInfo = response.body().Data.get(0);
                                    mSettings.Value.TKN_03 = userInfo.TKN_03;
                                    mSettings.putStringItem(SettingsKey.TKN_03, userInfo.TKN_03);

                                    onRequestLoginListener.isSuccess(response.body().Data.get(0));
                                } else {
                                    onRequestLoginListener.isFail(response.body().Data.get(0).ErrorCode);
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
}
