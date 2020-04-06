
package kr.hmit.afbis.ui.login;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import kr.hmit.afbis.R;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.base_alret.BaseAlert;
import kr.hmit.base.util.PermissionUtils;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initialize() {
        if (!PermissionUtils.checkPermissionAll(mContext))
            PermissionUtils.requestPermissionAll(mActivity);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PermissionUtils.REQUEST_CODE) {
            PermissionUtils.checkPermissionResult(mActivity, permissions, grantResults, new PermissionUtils.OnPermissionListener() {
                @Override
                public void permissionGranted() {

                }

                @Override
                public void permissionDenied() {
                    BaseAlert.show(mContext, "권한을 허용하지 않으면 앱을 사용할 수 없습니다.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            PermissionUtils.goAppSettings(mContext);
                            finish();
                        }
                    });
                }
            });
        }
    }
}
