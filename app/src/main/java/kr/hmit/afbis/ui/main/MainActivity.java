package kr.hmit.afbis.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kr.hmit.afbis.R;
import kr.hmit.afbis.ui.board.BoardFragment;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.base_view_pager.BaseViewPager;
import kr.hmit.base.base_view_pager.ViewPagerAdapter;
import kr.hmit.base.util.PermissionUtils;

public class MainActivity extends BaseActivity {
    //================================
    // final
    //================================
    public static final int MEDIA_TYPE_IMAGE = 1;

    private int PAGE_BOARD;
    private int PAGE_CALENDAR;

    //================================
    // layout
    //================================
    private BaseViewPager viewPager;
    private BoardFragment fragmentBoard;

    private ImageView imgMenu1;
    private ImageView imgMenu2;
    private ImageView imgMenu3;
    private ImageView imgMenu4;
    private ImageView imgMenu5;
    private ImageView imgMenu6;

    //================================
    // variable
    //================================
    private ViewPagerAdapter mViewPagerAdapter;
    private List<Fragment> mListFragment;

    //================================
    // initialize
    //================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        imgMenu2 = findViewById(R.id.imgMenu2);
        imgMenu3 = findViewById(R.id.imgMenu3);

        initViewPager();
    }

    /**
     * Viewpager 초기화
     */
    private void initViewPager() {
        viewPager = findViewById(R.id.viewPagerMain);

        fragmentBoard = new BoardFragment();

        mListFragment = new ArrayList<>();

        int nIndex = 0;
        mListFragment.add(fragmentBoard);
        PAGE_BOARD = nIndex++;

        mViewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), mListFragment);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(mListFragment.size() - 1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(PAGE_BOARD);
    }

    private void setPage(int position) {
        imgMenu2.setSelected(false);
        imgMenu3.setSelected(false);

        if (position == PAGE_BOARD) {
            imgMenu2.setSelected(true);
        } else {
            imgMenu3.setSelected(true);
        }
    }

    @Override
    protected void initialize() {

    }



    private File fileTakePhoto;
    private Uri fileUri;

    private void goCamera() {
        if (!PermissionUtils.checkPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || !PermissionUtils.checkPermission(mContext, Manifest.permission.CAMERA)) {
            PermissionUtils.requestPermission(mActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
            return;
        }


        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileTakePhoto = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        fileUri = FileProvider.getUriForFile(mContext, mSettings.Value.FileProviderPath, fileTakePhoto);

        List<ResolveInfo> resolvedIntentActivities = mContext.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolvedIntentInfo : resolvedIntentActivities) {
            String packageName = resolvedIntentInfo.activityInfo.packageName;

            mContext.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PHOTO);
    }

    @SuppressLint("SimpleDateFormat")
    private File getOutputMediaFile(int type) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "smfactory");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD_HHMMSS, Locale.US).format(new Date());
        File mediaFile;

        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + mSettings.Value.LoginID + "_IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }
}
