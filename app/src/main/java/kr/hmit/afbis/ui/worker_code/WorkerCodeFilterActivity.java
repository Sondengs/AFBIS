package kr.hmit.afbis.ui.worker_code;

import android.os.Bundle;

import kr.hmit.afbis.databinding.ActivityWorkerCodeFilterBinding;
import kr.hmit.base.base_activity.BaseActivity;

public class WorkerCodeFilterActivity extends BaseActivity {
    //================================
    // region // view
    private ActivityWorkerCodeFilterBinding binding;
    // endregion // view
    //================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkerCodeFilterBinding.inflate(getLayoutInflater());
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