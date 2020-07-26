package kr.hmit.afbis;

import android.os.Bundle;
import android.util.Log;

import kr.hmit.afbis.model.response.MEM_ReadModel;
import kr.hmit.afbis.model.response.WKS_Model;
import kr.hmit.afbis.network.RequestAPI;
import kr.hmit.base.base_activity.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    @Override
    protected void initLayout() {
        RequestAPI.WKS_05(mActivity, restResult -> {
            if (restResult != null) {
                WKS_Model model = (WKS_Model) restResult.mData;

                Log.d("Test", "WKS_05-" + model.Total + " - " + model.Data.get(0).WKS_05);
            } else {

            }
        });
        RequestAPI.MEM_Read(mActivity, restResult -> {
            if (restResult != null) {
                MEM_ReadModel model = (MEM_ReadModel) restResult.mData;

                Log.d("Test", "MEM_Read-" + model.Total + " - " + model.Data.get(0).MEM_32_NM);
            } else {

            }
        });
        RequestAPI.WKS_Read(mActivity, restResult -> {
            if (restResult != null) {
                WKS_Model model = (WKS_Model) restResult.mData;

                Log.d("Test", "WKS_Read-" + model.Total + " - " + model.Data.get(0).WKS_07);
            } else {

            }
        });
        RequestAPI.WKS_Detail(mActivity, restResult -> {
            if (restResult != null) {
                WKS_Model model = (WKS_Model) restResult.mData;

                Log.d("Test", "WKS_Detail - " + model.Total + " - " + model.Data.get(0).WKS_07);
            } else {

            }
        }, "385");
    }

    @Override
    protected void initialize() {

    }

}
