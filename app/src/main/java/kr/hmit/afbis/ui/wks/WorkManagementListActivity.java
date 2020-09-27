package kr.hmit.afbis.ui.wks;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityWorkManagementListBinding;
import kr.hmit.afbis.model.response.WKS_Model;
import kr.hmit.afbis.model.vo.WKS_VO;
import kr.hmit.afbis.network.Http;
import kr.hmit.afbis.ui.wks.write_work.WriteWorkActivity;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.base_alret.BaseAlert;
import kr.hmit.base.network.BaseConst;
import kr.hmit.base.network.ClsNetworkCheck;
import kr.hmit.base.network.HttpBaseService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        binding.imgBack.setOnClickListener(v -> finish());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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

        binding.imgWriteWork.setOnClickListener(this::onClickGoWriteWork);
    }


    @Override
    protected void initialize() {
        mListTotal = new ArrayList<>();
        mListSearch = new ArrayList<>();
        mAdapter = new WorkManagementListAdapter(mContext, mListTotal);
        mAdapter.setOnItemClickListener(this::onItemClickGoDetail);
        binding.recyclerView.setAdapter(mAdapter);

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
        if (!ClsNetworkCheck.isConnectable(mContext)) {
            BaseAlert.show(mContext, R.string.network_error_1);
            return;
        }

        openLoadingBar();

        Http.wks(HttpBaseService.TYPE.GET, BaseConst.URL_HOST).WKS_Read(
                BaseConst.URL_HOST,
                mUser.Value.MEM_CID,
                mUser.Value.MEM_01,
                mUser.Value.TKN_03,
                "M_LIST",
                mUser.Value.MEM_CID,
                "유대성"
        ).enqueue(new Callback<WKS_Model>() {
            @SuppressLint("HandlerLeak")
            @Override
            public void onResponse(Call<WKS_Model> call, Response<WKS_Model> response) {
                Message msg = new Message();
                msg.obj = response;
                msg.what = 100;

                //=====================
                // response callback
                //=====================
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if (msg.what == 100) {
                            closeLoadingBar();

                            Response<WKS_Model> response = (Response<WKS_Model>) msg.obj;

                            if (response.isSuccessful()) {
                                if (response.body().Data.get(0).Validation) {
                                    bindingData(response.body().Data);
                                } else {
                                    BaseAlert.show(mContext, getString(R.string.network_error_2) + "-" + response.body().Data.get(0).ErrorCode);
                                }
                            } else {
                                BaseAlert.show(mContext, getString(R.string.network_error_2) + "-" + response.errorBody());
                            }
                        }
                    }
                }.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<WKS_Model> call, Throwable t) {
                closeLoadingBar();
                BaseAlert.show(mContext, R.string.network_error_2);
            }
        });
    }

    /**
     * 서버 데이터 바인딩
     *
     * @param data
     */
    private void bindingData(ArrayList<WKS_VO> data) {
        mListTotal = data;

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


    private void onClickGoWriteWork(View v) {
        goActivity(WriteWorkActivity.class);
    }

    //==============================
    // endregion // event
    //==============================
}
