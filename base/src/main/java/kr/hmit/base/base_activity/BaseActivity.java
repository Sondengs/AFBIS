package kr.hmit.base.base_activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import kr.hmit.base.BaseApplication;
import kr.hmit.base.R;
import kr.hmit.base.settings.InterfaceSettings;


public abstract class BaseActivity extends FragmentActivity {
    public static Context BaseContext;
    protected Context mContext;
    protected Activity mActivity;
    protected InterfaceSettings mSettings;
//    protected InterfaceUser mUser;
//    private BaseLoadingBar mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.start_enter, R.anim.start_exit);

        init();
    }

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

//        if (mUser == null || mUser.Value == null || mUser.Value.EMP_ID == null || mUser.Value.EMP_ID.isEmpty()) {
//            if (!getClass().getSimpleName().equals("PermissionInfo") && !getClass().getSimpleName().equals("Intro") && !getClass().getSimpleName().equals("Login") && !getClass().getSimpleName().equals("SignUp"))
//                ClsUtil.forceRestartAppforActivity(mActivity);
//        }
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
}
