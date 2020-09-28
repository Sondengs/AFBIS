package kr.hmit.afbis.ui.product_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityAddProductInfoBinding;
import kr.hmit.base.base_activity.BaseActivity;

public class AddProductInfoActivity extends BaseActivity {
//================================
    // region // view
    //================================

    private ActivityAddProductInfoBinding binding;

    //================================
    // endregion // view
    //================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductInfoBinding.inflate(getLayoutInflater());
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