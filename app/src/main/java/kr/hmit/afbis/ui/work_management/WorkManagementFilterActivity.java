package kr.hmit.afbis.ui.work_management;

import android.os.Bundle;

import kr.hmit.afbis.databinding.ActivityWorkManagementFilterBinding;
import kr.hmit.base.base_activity.BaseActivity;

public class WorkManagementFilterActivity extends BaseActivity {
    //================================
    // region // view
    private ActivityWorkManagementFilterBinding binding;
    // endregion // view
    //================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkManagementFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initialize() {

    }
}