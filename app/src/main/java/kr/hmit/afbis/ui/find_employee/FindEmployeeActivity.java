package kr.hmit.afbis.ui.find_employee;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.annimon.stream.function.Consumer;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ActivityFindEmployeeBinding;
import kr.hmit.afbis.model.response.MEM_ReadModel;
import kr.hmit.afbis.model.vo.MEM_ReadVO;
import kr.hmit.afbis.network.RequestAPI;
import kr.hmit.afbis.network.RestResult;
import kr.hmit.base.base_activity.BaseActivity;

public class FindEmployeeActivity extends BaseActivity {
    //===========================
    // view
    //===========================
    private ActivityFindEmployeeBinding binding;


    //===========================
    // variable
    //===========================
    private FindEmployeeAdapter mAdapter;


    //===========================
    // initialize
    //===========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFindEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        binding.imgBack.setOnClickListener(this::onClickBack);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    /**
     * 닫기
     *
     * @param v
     */
    private void onClickBack(View v) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void initialize() {
        mAdapter = new FindEmployeeAdapter(mContext, new ArrayList<>());

        binding.recyclerView.setAdapter(mAdapter);
        requestMEM_Read();
    }


    /**
     * api 호출
     */
    private void requestMEM_Read() {
        RequestAPI.MEM_Read(mActivity, new Consumer<RestResult>() {
            @Override
            public void accept(RestResult restResult) {
                MEM_ReadModel model = (MEM_ReadModel) restResult.mData;

                if (model.Data.get(0).Validation) {
                    mAdapter.update(model.Data);
                }
            }
        });
    }
}
