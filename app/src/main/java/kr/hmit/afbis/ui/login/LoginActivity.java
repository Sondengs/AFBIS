
package kr.hmit.afbis.ui.login;

import android.os.Bundle;
import android.text.TextUtils;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityLoginBinding;
import kr.hmit.afbis.ui.main.MainDashboardActivity;
import kr.hmit.afbis.ui.wks.WorkManagementListActivity;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.base_alret.BaseAlert;
import kr.hmit.base.user_interface.UserInfo;

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
            binding.btnLogin.performClick();
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

        requestLogin();
    }


    //=========================
    // api
    //=========================

    /**
     * 로그인을 한다.
     */
    private void requestLogin() {
        requestLogin(new OnRequestLogin() {
            @Override
            public void isSuccess(UserInfo userInfo) {
                goMain();
            }

            @Override
            public void isFail(String errorMsg) {

            }

            @Override
            public void isNotConnectable() {
                BaseAlert.show(mContext, R.string.network_error_1);
            }
        });
    }

    /**
     * 메인으로 간다.
     */
    private void goMain() {
//        goActivity(WorkManagementListActivity.class);
        goActivity(MainDashboardActivity.class);
        finish();
    }


    //=========================
    // event
    //=========================

}
