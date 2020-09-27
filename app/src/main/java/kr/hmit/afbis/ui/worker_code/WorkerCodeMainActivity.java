package kr.hmit.afbis.ui.worker_code;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.databinding.ActivityWorkerCodeMainBinding;
import kr.hmit.afbis.ui.worker_code.adapter.WorkerCodeMainAdapter;
import kr.hmit.afbis.ui.worker_code.model.WorkerCodeVO;
import kr.hmit.base.base_activity.BaseActivity;

public class WorkerCodeMainActivity extends BaseActivity {
    //================================
    // region // view
    private ActivityWorkerCodeMainBinding binding;
    // endregion // view
    //================================


    //================================
    // region // variable
    private WorkerCodeMainAdapter mAdapter;
    private ArrayList<WorkerCodeVO> mListTotal;
    private ArrayList<WorkerCodeVO> mListSearch;
    // endregion // variable
    //================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkerCodeMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        binding.imgBack.setOnClickListener(v -> finish());
        binding.recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
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
        mListTotal = new ArrayList<>();
        mListSearch = new ArrayList<>();

        if (BuildConfig.DEBUG) {
            String data = "[{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0001\",\"WOC_02\":\"홍길동\",\"WOC_03\":\"19020115\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20200415\",\"WOC_0702\":\"\",\"WOC_08\":\"남악리\",\"WOC_09\":\"공정테스트\",\"WOC_10\":\"사원\",\"WOC_11\":\"01012345689\",\"WOC_12\":\"20200417\",\"WOC_13\":\"스마트공장\",\"WOC_80\":0,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1560497277587)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0002\",\"WOC_02\":\"임꺽정\",\"WOC_03\":\"20190604\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20190604\",\"WOC_0702\":\"\",\"WOC_08\":\"남악\",\"WOC_09\":\"생산공정테스트\",\"WOC_10\":\"주임1\",\"WOC_11\":\"01045678941\",\"WOC_12\":\"20191115\",\"WOC_13\":\"스마트공장\",\"WOC_80\":20,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1560746595930)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0004\",\"WOC_02\":\"나는야\",\"WOC_03\":\"19850723\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20060101\",\"WOC_0702\":\"\",\"WOC_08\":\"ㄴㅁㅇㄹ3\",\"WOC_09\":\"3333\",\"WOC_10\":\"3333\",\"WOC_11\":\"010-600-6666\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":40,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1502255171997)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0006\",\"WOC_02\":\"임희정\",\"WOC_03\":\"19900607\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20170327\",\"WOC_0702\":\"\",\"WOC_08\":\"부산광역시 사상구 사상동 현대힐스테이트 105동\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"010-1234-8857\",\"WOC_12\":\"20180101\",\"WOC_13\":\"010-1234-8857\",\"WOC_80\":60,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1508470696253)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0007\",\"WOC_02\":\"최석준\",\"WOC_03\":\"19910707\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20160807\",\"WOC_0702\":\"\",\"WOC_08\":\"부산광역시 수영구 수영동 오피스텔 405호\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"010-9987-5654\",\"WOC_12\":\"20171005\",\"WOC_13\":\"010-9987-5654\",\"WOC_80\":70,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1508470745493)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0008\",\"WOC_02\":\"최홍만\",\"WOC_03\":\"19820607\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20171117\",\"WOC_0702\":\"\",\"WOC_08\":\"광주광역시 연산동 504호\",\"WOC_09\":\"\",\"WOC_10\":\"과장\",\"WOC_11\":\"010-3434-2323\",\"WOC_12\":\"20191123\",\"WOC_13\":\"010-9898-5655\",\"WOC_80\":80,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1508470806243)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0009\",\"WOC_02\":\"이명박\",\"WOC_03\":\"19780927\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20111228\",\"WOC_0702\":\"\",\"WOC_08\":\"서울특별시 강남구 대치동 타워펠리스 305호\",\"WOC_09\":\"\",\"WOC_10\":\"대리\",\"WOC_11\":\"010-5568-9898\",\"WOC_12\":\"\",\"WOC_13\":\"010-5568-9898\",\"WOC_80\":90,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1508470865607)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0010\",\"WOC_02\":\"안철수\",\"WOC_03\":\"19580730\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"19850228\",\"WOC_0702\":\"\",\"WOC_08\":\"서울특별시 서초구 서초동 405\",\"WOC_09\":\"\",\"WOC_10\":\"이사\",\"WOC_11\":\"010-4454-5545\",\"WOC_12\":\"20171010\",\"WOC_13\":\"010-4454-5545\",\"WOC_80\":100,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1508470948687)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0011\",\"WOC_02\":\"ㅋㅋㅋㅋ\",\"WOC_03\":\"19890906\",\"WOC_04\":\"F\",\"WOC_05\":\"2\",\"WOC_06\":true,\"WOC_0701\":\"20171109\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"부장\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":110,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1511164583350)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0012\",\"WOC_02\":\"길동이\",\"WOC_03\":\"20171117\",\"WOC_04\":\"M\",\"WOC_05\":\"2\",\"WOC_06\":false,\"WOC_0701\":\"20171122\",\"WOC_0702\":\"\",\"WOC_08\":\"ㄴㅇㅁㄹㄴㅇㅁㄹㄴㅇㅁ\",\"WOC_09\":\"\",\"WOC_10\":\"차장\",\"WOC_11\":\"234324234234\",\"WOC_12\":\"20191120\",\"WOC_13\":\"\",\"WOC_80\":120,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1511164614410)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0013\",\"WOC_02\":\"최순실\",\"WOC_03\":\"19581025\",\"WOC_04\":\"F\",\"WOC_05\":\"2\",\"WOC_06\":false,\"WOC_0701\":\"20171213\",\"WOC_0702\":\"\",\"WOC_08\":\"경기도 과천시 관문로 47 정부과천청사 1동 법무부 교정본부\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"02)9502-9502\",\"WOC_12\":\"20231231\",\"WOC_13\":\"\",\"WOC_80\":130,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1513125171640)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0014\",\"WOC_02\":\"강하나\",\"WOC_03\":\"19981113\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20191104\",\"WOC_0702\":\"\",\"WOC_08\":\"1\",\"WOC_09\":\"\",\"WOC_10\":\"주임\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":140,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1573055659607)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0016\",\"WOC_02\":\"테스터\",\"WOC_03\":\"\",\"WOC_04\":\"M\",\"WOC_05\":\"\",\"WOC_06\":false,\"WOC_0701\":\"20191215\",\"WOC_0702\":\"\",\"WOC_08\":\"남악신도시\",\"WOC_09\":\"\",\"WOC_10\":\"비정규\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":160,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1576391450910)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0017\",\"WOC_02\":\"테스트(나비)\",\"WOC_03\":\"\",\"WOC_04\":\"M\",\"WOC_05\":\"2\",\"WOC_06\":false,\"WOC_0701\":\"20191211\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":170,\"WOC_98\":\"ywkim\",\"WOC_99\":\"/Date(1576406134597)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0018\",\"WOC_02\":\"김나라\",\"WOC_03\":\"20180506\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20191201\",\"WOC_0702\":\"\",\"WOC_08\":\"목포시 용해동 \",\"WOC_09\":\"불량 검사\",\"WOC_10\":\"사원\",\"WOC_11\":\"01084747362\",\"WOC_12\":\"20191203\",\"WOC_13\":\"01088834444\",\"WOC_80\":180,\"WOC_98\":\"hrkim\",\"WOC_99\":\"/Date(1576422590077)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0020\",\"WOC_02\":\"테스트\",\"WOC_03\":\"19911225\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20191216\",\"WOC_0702\":\"\",\"WOC_08\":\"나아암악\",\"WOC_09\":\"음료제작\",\"WOC_10\":\"사원\",\"WOC_11\":\"01026547896\",\"WOC_12\":\"20200116\",\"WOC_13\":\"\",\"WOC_80\":190,\"WOC_98\":\"esyun\",\"WOC_99\":\"/Date(1576455164800)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0021\",\"WOC_02\":\"면봉장인\",\"WOC_03\":\"\",\"WOC_04\":\"M\",\"WOC_05\":\"\",\"WOC_06\":false,\"WOC_0701\":\"20191216\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":200,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1576469084683)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0022\",\"WOC_02\":\"김만두\",\"WOC_03\":\"20191217\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":true,\"WOC_0701\":\"20191215\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"장인\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":1,\"WOC_98\":\"ssol\",\"WOC_99\":\"/Date(1576472026407)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0023\",\"WOC_02\":\"작업자\",\"WOC_03\":\"19880818\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20191216\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"\",\"WOC_12\":\"20191216\",\"WOC_13\":\"\",\"WOC_80\":0,\"WOC_98\":\"ssol\",\"WOC_99\":\"/Date(1576474200320)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0024\",\"WOC_02\":\"술장인\",\"WOC_03\":\"\",\"WOC_04\":\"\",\"WOC_05\":\"\",\"WOC_06\":false,\"WOC_0701\":\"20191216\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":210,\"WOC_98\":\"dgoh\",\"WOC_99\":\"/Date(1576487821837)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0025\",\"WOC_02\":\"김양반\",\"WOC_03\":\"20191218\",\"WOC_04\":\"M\",\"WOC_05\":\"2\",\"WOC_06\":true,\"WOC_0701\":\"20191216\",\"WOC_0702\":\"20191217\",\"WOC_08\":\"남악\",\"WOC_09\":\"\",\"WOC_10\":\"사원\",\"WOC_11\":\"01082444444\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":220,\"WOC_98\":\"ywkim\",\"WOC_99\":\"/Date(1576543725927)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0026\",\"WOC_02\":\"김수미\",\"WOC_03\":\"19600420\",\"WOC_04\":\"F\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"19980326\",\"WOC_0702\":\"20191230\",\"WOC_08\":\"벌교\",\"WOC_09\":\"\",\"WOC_10\":\"겉절이무형문화재\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":230,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1576733607767)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0028\",\"WOC_02\":\"박주명\",\"WOC_03\":\"\",\"WOC_04\":\"\",\"WOC_05\":\"\",\"WOC_06\":false,\"WOC_0701\":\"20200423\",\"WOC_0702\":\"\",\"WOC_08\":\"\",\"WOC_09\":\"\",\"WOC_10\":\"\",\"WOC_11\":\"\",\"WOC_12\":\"\",\"WOC_13\":\"\",\"WOC_80\":250,\"WOC_98\":\"admin\",\"WOC_99\":\"/Date(1587613190240)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0029\",\"WOC_02\":\"HUMAN0727\",\"WOC_03\":\"19020115\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20200415\",\"WOC_0702\":\"\",\"WOC_08\":\"남악리\",\"WOC_09\":\"공정테스트\",\"WOC_10\":\"사원\",\"WOC_11\":\"01012345689\",\"WOC_12\":\"20200417\",\"WOC_13\":\"스마트공장\",\"WOC_80\":260,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1595814424470)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null},{\"WOC_ID\":\"TEST\",\"WOC_01\":\"0030\",\"WOC_02\":\"HUMAN0728\",\"WOC_03\":\"19020115\",\"WOC_04\":\"M\",\"WOC_05\":\"1\",\"WOC_06\":false,\"WOC_0701\":\"20200415\",\"WOC_0702\":\"\",\"WOC_08\":\"남악리\",\"WOC_09\":\"공정테스트\",\"WOC_10\":\"사원\",\"WOC_11\":\"01012345689\",\"WOC_12\":\"20200417\",\"WOC_13\":\"스마트공장\",\"WOC_80\":270,\"WOC_98\":\"dyjung\",\"WOC_99\":\"/Date(1595894722760)/\",\"RetVal\":null,\"MEM_02\":null,\"MEM_32\":null,\"isChecked\":false,\"ParentLOT_01\":null,\"DELTECHECK_MESSAGE\":null,\"Validation\":\"true\",\"SUCCESS\":null,\"ERROR_MSG\":null}]";
            mListTotal = new Gson().fromJson(data, new TypeToken<ArrayList<WorkerCodeVO>>() {
            }.getType());
        }


        mAdapter = new WorkerCodeMainAdapter(mContext, mListTotal);
        binding.recyclerView.setAdapter(mAdapter);
    }


    private void onClickGoTop(View view) {
        binding.recyclerView.smoothScrollToPosition(0);
    }


    private void onClickSearch(View view) {
        String strSearch = binding.etSearch.getText().toString().toUpperCase().trim();

        if (strSearch.isEmpty()) {
            mAdapter.update(mListTotal);
            return;
        }

        mListSearch.clear();

        for (int i = 0; i < mListTotal.size(); i++) {
            WorkerCodeVO vo = mListTotal.get(i);

            if (vo.WOC_02.toUpperCase().contains(strSearch)
                    || vo.WOC_09.toUpperCase().contains(strSearch)
                    || vo.WOC_10.toUpperCase().contains(strSearch)) {
                mListSearch.add(vo);
            }
        }

        mAdapter.update(mListSearch);
    }
}