package kr.hmit.afbis.ui.release;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.databinding.ActivityReleaseMainBinding;
import kr.hmit.afbis.ui.product_info.adapter.ProductionInfoListAdapter;
import kr.hmit.afbis.ui.product_info.model.ProductionInfoVO;
import kr.hmit.afbis.ui.release.adapter.ReleaseListAdapter;
import kr.hmit.afbis.ui.release.model.ReleaseVO;
import kr.hmit.base.base_activity.BaseActivity;

public class ReleaseMainActivity extends BaseActivity {
    //=============================
    // region // view
    //=============================

    private ActivityReleaseMainBinding binding;

    //=============================
    // endregion // view
    //=============================

    //=============================
    // region // variable
    //=============================

    private ReleaseListAdapter mAdapter;
    private ArrayList<ReleaseVO> mListTotal;
    private ArrayList<ReleaseVO> mListSearch;

    //=============================
    // endregion // variable
    //=============================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReleaseMainBinding.inflate(getLayoutInflater());
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
        binding.etSearch.setOnEditorActionListener(this::onSearch);
    }

    @Override
    protected void initialize() {
        mListTotal = new ArrayList<>();
        mListSearch = new ArrayList<>();
        if (BuildConfig.DEBUG) {
            String data = "[{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q180801-002\",\"REQ_02\":\"20180801\",\"REQ_03\":\"A0003\",\"REQ_04\":\"R1002T0043\",\"REQ_05\":2222,\"REQ_06\":222,\"REQ_07\":493284,\"REQ_08\":\"20180801\",\"REQ_09\":\"009\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":101,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1554438826607)/\",\"REQ_03_NM\":\"오가닉\",\"REQ_09_NM\":\"인터넷판매\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190321-001\",\"REQ_02\":\"20181231\",\"REQ_03\":\"G0001\",\"REQ_04\":\"R1011T0033\",\"REQ_05\":323,\"REQ_06\":10,\"REQ_07\":3230,\"REQ_08\":\"20181231\",\"REQ_09\":\"009\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"1004\",\"REQ_99\":\"/Date(1553132616240)/\",\"REQ_03_NM\":\"김이산\",\"REQ_09_NM\":\"인터넷판매\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"전무이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190321-002\",\"REQ_02\":\"20181231\",\"REQ_03\":\"G0001\",\"REQ_04\":\"R1002T0012\",\"REQ_05\":5000,\"REQ_06\":10,\"REQ_07\":50000,\"REQ_08\":\"20181231\",\"REQ_09\":\"009\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"1004\",\"REQ_99\":\"/Date(1553132616247)/\",\"REQ_03_NM\":\"김이산\",\"REQ_09_NM\":\"인터넷판매\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"전무이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190409-002\",\"REQ_02\":\"20190201\",\"REQ_03\":\"G0001\",\"REQ_04\":\"P1017T0049\",\"REQ_05\":2000,\"REQ_06\":1,\"REQ_07\":2000,\"REQ_08\":\"20190202\",\"REQ_09\":\"009\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"1004\",\"REQ_99\":\"/Date(1554782055273)/\",\"REQ_03_NM\":\"김이산\",\"REQ_09_NM\":\"인터넷판매\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"전무이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190403-001\",\"REQ_02\":\"20190403\",\"REQ_03\":\"G0005\",\"REQ_04\":\"P1017T0049\",\"REQ_05\":2000,\"REQ_06\":10,\"REQ_07\":20000,\"REQ_08\":\"20190403\",\"REQ_09\":\"020\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"1004\",\"REQ_99\":\"/Date(1555555373553)/\",\"REQ_03_NM\":\"임희정\",\"REQ_09_NM\":\"내방\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"전무이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190710-001\",\"REQ_02\":\"20190710\",\"REQ_03\":\"0005\",\"REQ_04\":\"R1T0003\",\"REQ_05\":0,\"REQ_06\":2,\"REQ_07\":0,\"REQ_08\":\"20190710\",\"REQ_09\":\"001\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1562740602107)/\",\"REQ_03_NM\":\"\",\"REQ_09_NM\":\"판매테스트\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190710-003\",\"REQ_02\":\"20190710\",\"REQ_03\":\"G0002\",\"REQ_04\":\"R1T0003\",\"REQ_05\":0,\"REQ_06\":2,\"REQ_07\":0,\"REQ_08\":\"20190710\",\"REQ_09\":\"001\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1562744843733)/\",\"REQ_03_NM\":\"\",\"REQ_09_NM\":\"판매테스트\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q190710-002\",\"REQ_02\":\"20190710\",\"REQ_03\":\"G0001\",\"REQ_04\":\"R1T0003\",\"REQ_05\":0,\"REQ_06\":2,\"REQ_07\":0,\"REQ_08\":\"20190710\",\"REQ_09\":\"001\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1562740852420)/\",\"REQ_03_NM\":\"김이산\",\"REQ_09_NM\":\"판매테스트\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q191106-001\",\"REQ_02\":\"20191106\",\"REQ_03\":\"A0005\",\"REQ_04\":\"Sudong\",\"REQ_05\":22222,\"REQ_06\":111,\"REQ_07\":2466642,\"REQ_08\":\"20191106\",\"REQ_09\":\"001\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":0,\"REQ_6005\":0,\"REQ_6006\":\"L191106-001\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1573046423290)/\",\"REQ_03_NM\":\"고성민테스트\",\"REQ_09_NM\":\"판매테스트\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null},{\"REQ_ID\":\"TEST\",\"REQ_01\":\"Q200511-001\",\"REQ_02\":\"20200511\",\"REQ_03\":\"A0009\",\"REQ_04\":\"P1017T0049\",\"REQ_05\":2000,\"REQ_06\":100,\"REQ_07\":200000,\"REQ_08\":\"20200511\",\"REQ_09\":\"026\",\"REQ_10\":null,\"REQ_12\":null,\"REQ_11\":null,\"REQ_6001\":0,\"REQ_6002\":0,\"REQ_6003\":0,\"REQ_6004\":11,\"REQ_6005\":0,\"REQ_6006\":\"\",\"REQ_97\":\"\",\"REQ_98\":\"admin\",\"REQ_99\":\"/Date(1589189266730)/\",\"REQ_03_NM\":\"집앞마트\",\"REQ_09_NM\":\"판매유형\",\"D_DAY\":null,\"REQ_6004N\":0,\"REQ_CK\":null,\"MEM_32_NM\":\"이사\",\"REQ_10_NM\":null,\"REQ_11_NM\":null,\"REQ_12_NM\":null,\"EDAY\":null,\"Validation\":\"true\",\"runModels\":null,\"SUCCESS\":null,\"ERROR_MSG\":null}]";
            mListTotal = new Gson().fromJson(data, new TypeToken<ArrayList<ReleaseVO>>() {
            }.getType());
        }

        mAdapter = new ReleaseListAdapter(mContext, mListTotal);
        binding.recyclerView.setAdapter(mAdapter);
    }


    private boolean onSearch(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onClickSearch(v);
        } else {
            return false;
        }

        return true;
    }

    /**
     * 상단으로 올린다.
     *
     * @param v
     */
    private void onClickGoTop(View v) {
        binding.recyclerView.smoothScrollToPosition(0);
    }

    /**
     * 검색 버튼 클릭 시
     *
     * @param v
     */
    private void onClickSearch(View v) {
        String strSearch = binding.etSearch.getText().toString().toUpperCase().trim();

        if (strSearch.isEmpty()) {
            mAdapter.update(mListTotal);
            return;
        }

        mListSearch.clear();

        for (int i = 0; i < mListTotal.size(); i++) {
            ReleaseVO vo = mListTotal.get(i);

            if (vo.REQ_04.toUpperCase().contains(strSearch)
                    || vo.REQ_03_NM.toUpperCase().contains(strSearch)
                    || vo.REQ_02.toUpperCase().contains(strSearch)
                    || vo.REQ_01.toUpperCase().contains(strSearch)) {
                mListSearch.add(vo);
            }
        }

        mAdapter.update(mListSearch);
    }
}