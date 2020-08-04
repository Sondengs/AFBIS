package kr.hmit.afbis.ui.wks;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ActivityWorkManagementListBinding;
import kr.hmit.afbis.model.response.WKS_Model;
import kr.hmit.afbis.model.vo.WKS_VO;
import kr.hmit.afbis.network.RequestAPI;
import kr.hmit.base.base_activity.BaseActivity;

public class WorkManagementListActivity extends BaseActivity {
    //=============================
    // region // view
    //=============================

    private ActivityWorkManagementListBinding binding;

    //=============================
    // endregion
    //=============================

    //=============================
    // region // variable
    //=============================
    private WorkManagementListAdapter mAdapter;
    private ArrayList<WKS_VO> mListTotal;
    private ArrayList<WKS_VO> mListSearch;

    //=============================
    // endregion // variable
    //=============================

    //=============================
    // region // initialize
    //=============================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkManagementListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        mListTotal = new ArrayList<>();
        mListSearch = new ArrayList<>();
        mAdapter = new WorkManagementListAdapter(mContext, mListTotal);
        mAdapter.setOnItemClickListener(this::onItemClickGoDetail);
        binding.recyclerView.setAdapter(mAdapter);

        binding.imgGoTop.setOnClickListener(this::onClickGoTop);
        binding.imgSearch.setOnClickListener(this::onClickSearch);
        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    onClickSearch(v);
                    break;
                default:
                    return false;
            }

            return true;
        });
    }


    @Override
    protected void initialize() {
        requestWKS_Read();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //=============================
    // endregion // initialize
    //=============================

    //=============================
    // region // api
    //=============================

    /**
     * 업무관리 목록 조회
     */
    private void requestWKS_Read() {
        RequestAPI.WKS_Read(mActivity, restResult -> {
            if (restResult != null) {
                WKS_Model model = (WKS_Model) restResult.mData;

                bindingData(model);
            } else {

            }
        });
    }

    /**
     * 서버 데이터 바인딩
     *
     * @param model
     */
    private void bindingData(WKS_Model model) {
        mListTotal = model.Data;

        mAdapter.update(mListTotal);
    }

    //=============================
    // endregion // api
    //=============================

    //==============================
    // region // event
    //==============================

    /**
     * 상단으로 올린다.
     *
     * @param v
     */
    private void onClickGoTop(View v) {
        binding.recyclerView.smoothScrollToPosition(0);
    }

    /**
     * 상세화면으로 이동
     *
     * @param view
     * @param position
     */
    private void onItemClickGoDetail(View view, int position) {
    }

    /**
     * 검색 버튼 클릭 시
     *
     * @param v
     */
    private void onClickSearch(View v) {
        String strSearch = binding.etSearch.getText().toString().trim();

        if (strSearch.isEmpty()) {
            mAdapter.update(mListTotal);
            return;
        }

        mListSearch.clear();

        for (int i = 0; i < mListTotal.size(); i++) {
            WKS_VO vo = mListTotal.get(i);

            if (vo.WKS_98.contains(strSearch)
                    || vo.WKS_1001_NM.contains(strSearch)
                    || vo.WKS_1101_NM.contains(strSearch)
                    || vo.WKS_1201_NM.contains(strSearch)
                    || vo.WKS_1301_NM.contains(strSearch)
                    || vo.WKS_04.contains(strSearch)) {
                mListSearch.add(vo);
            }
        }

        mAdapter.update(mListSearch);
    }
    //==============================
    // endregion // event
    //==============================
}
