package kr.hmit.afbis.ui.find_employee;

import android.os.Bundle;
import android.view.View;

import kr.hmit.afbis.databinding.ActivityFindEmployeeBinding;
import kr.hmit.base.base_activity.BaseActivity;

public class FindEmployeeActivity extends BaseActivity {
    //===========================
    // view
    //===========================
    private ActivityFindEmployeeBinding binding;


    //===========================
    // variable
    //===========================


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

    }
}
