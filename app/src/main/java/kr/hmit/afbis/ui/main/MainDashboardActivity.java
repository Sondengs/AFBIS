package kr.hmit.afbis.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;

import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityMainDashboardBinding;
import kr.hmit.afbis.ui.drawer_menu.DrawerMenuFragment;
import kr.hmit.afbis.ui.main_dashboard.MainDashboardFragment;
import kr.hmit.base.base_activity.BaseActivity;

public class MainDashboardActivity extends BaseActivity {
    //=========================
    // final
    //=========================


    //=========================
    // view
    //=========================
    private ActivityMainDashboardBinding binding;

    //=========================
    // variable
    //=========================


    //=========================
    // initialize
    //=========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        binding.imgMenu.setOnClickListener(v -> showDrawerMenu());

        binding.imgClose.setOnClickListener(v -> showDrawerMenu());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.layoutFragment.getId(), MainDashboardFragment.newInstance("", ""))
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.layoutDrawer.getId(), DrawerMenuFragment.newInstance("", ""))
                .commit();

        binding.imgDrawerMenu.setOnClickListener(v -> toast("Menu"));
        binding.imgFavorite.setOnClickListener(v -> toast("Favorite"));
        binding.imgSettings.setOnClickListener(v -> toast("Settings"));
        binding.imgLogout.setOnClickListener(v -> toast("Logout"));
    }

    /**
     * Drawer Menu 보일 지 가릴 지
     */
    @SuppressLint("RtlHardcoded")
    private void showDrawerMenu() {
        if (binding.drawerLayout.isDrawerOpen(Gravity.LEFT))
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        else
            binding.drawerLayout.openDrawer(Gravity.LEFT);
    }

    @Override
    protected void initialize() {

    }
}
