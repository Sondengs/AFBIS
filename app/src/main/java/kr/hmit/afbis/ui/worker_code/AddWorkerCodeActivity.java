package kr.hmit.afbis.ui.worker_code;

import android.os.Bundle;

import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityAddWorkerCodeBinding;
import kr.hmit.afbis.databinding.ActivityWorkerCodeFilterBinding;
import kr.hmit.base.base_activity.BaseActivity;

public class AddWorkerCodeActivity extends BaseActivity {
    //================================
    // region // view
    //================================

    private ActivityAddWorkerCodeBinding binding;

    //================================
    // endregion // view
    //================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWorkerCodeBinding.inflate(getLayoutInflater());
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