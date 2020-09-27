package kr.hmit.afbis.ui.wks;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import kr.hmit.afbis.BuildConfig;
import kr.hmit.afbis.R;
import kr.hmit.afbis.databinding.ActivityWriteWorkBinding;
import kr.hmit.afbis.model.response.WKS_Model;
import kr.hmit.afbis.model.vo.MEM_ReadVO;
import kr.hmit.afbis.model.vo.WKS_VO;
import kr.hmit.afbis.network.Http;
import kr.hmit.afbis.ui.find_employee.FindEmployeeActivity;
import kr.hmit.afbis.ui.wks.adapter.AddWorkEmployeeAdapter;
import kr.hmit.base.base_activity.BaseActivity;
import kr.hmit.base.network.BaseConst;
import kr.hmit.base.network.ClsNetworkCheck;
import kr.hmit.base.network.HttpBaseService;
import kr.hmit.base.util.ClsDateTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddWorkActivity extends BaseActivity {
    //===============================
    // region // view
    //===============================
    private ActivityWriteWorkBinding binding;
    private String[] mCategory;
    private ArrayList<MEM_ReadVO> mListEmployee;
    private AddWorkEmployeeAdapter mAdapter;

    //===============================
    // endregion // view
    //===============================


    //===============================
    // region // variable
    //===============================
    private Calendar mCalRequest;
    private SimpleDateFormat sdfFormat;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mCalRequest.set(Calendar.YEAR, year);
            mCalRequest.set(Calendar.MONTH, month);
            mCalRequest.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            binding.tvRequestDate.setText(sdfFormat.format(mCalRequest.getTime()));
        }
    };

    //===============================
    // endregion // variable
    //===============================

    //===============================
    // region // initialize
    //===============================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteWorkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLayout();

        initialize();
    }

    @Override
    protected void initLayout() {
        binding.imgBack.setOnClickListener(v -> finish());

        initEmployeeList();

        binding.tvRequestDate.setOnClickListener(this::onClickRequestDate);
        binding.tvCategory.setOnClickListener(this::onClickCategory);
        binding.addEmployee.setOnClickListener(this::onClickAddEmployee);
    }

    /**
     * 담당자 recyclerView 초기화
     */
    private void initEmployeeList() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        mListEmployee = new ArrayList<>();
        mAdapter = new AddWorkEmployeeAdapter(mContext, mListEmployee);
        binding.recyclerView.setAdapter(mAdapter);
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void initialize() {
        mCategory = new String[]{getString(R.string.write_work_0)};
        sdfFormat = new SimpleDateFormat("yyyy-MM-dd");
        mCalRequest = Calendar.getInstance();
        binding.tvRequestDate.setText(sdfFormat.format(mCalRequest.getTime()));

        requestWKS_05(false);

        if (BuildConfig.DEBUG) {
            binding.etContents.setText("Test : " + ClsDateTime.getNow());
            binding.etDetail.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        }
    }

    //===============================
    // endregion // initialize
    //===============================


    //===============================
    // region // event
    //===============================

    /**
     * 담당자 추가 클릭 시
     *
     * @param v
     */
    private void onClickAddEmployee(View v) {
        goActivity(FindEmployeeActivity.class, FindEmployeeActivity.REQUEST_CODE);
    }

    /**
     * 요청일자 클릭
     *
     * @param v
     */
    private void onClickRequestDate(View v) {
        datePickerDialog = new DatePickerDialog(mContext, datePickerListener, mCalRequest.get(Calendar.YEAR),
                mCalRequest.get(Calendar.MONTH), mCalRequest.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void onClickCategory(View v) {
        if (mCategory != null && mCategory.length == 1) {
            requestWKS_05(true);
        } else {
            dropdownCategory();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 담당자 추가
        if (requestCode == FindEmployeeActivity.REQUEST_CODE && resultCode == RESULT_OK) {
            MEM_ReadVO vo = (MEM_ReadVO) data.getSerializableExtra(FindEmployeeActivity.EMPLOYEE_INFO);
            mListEmployee.add(vo);
            mAdapter.update(mListEmployee);
        }
    }

    //===============================
    // endregion // event
    //===============================

    //===============================
    // region // method
    //===============================

    /**
     * 업무분류 리스트를 보여준다.
     */
    private void dropdownCategory() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        builder.setTitle(R.string.write_work_1)
                .setItems(mCategory, (dialog, which) -> {
                    setCategory(which);

                }).setCancelable(false).create();

        builder.show();
    }

    /**
     * 선택된 업무분류를 화면에 설정한다.
     *
     * @param which
     */
    private void setCategory(int which) {
        // 0번일 때만 직접 입력
        if (which == 0) {
            binding.etCategory.setEnabled(true);
            binding.etCategory.setText("");
            binding.etCategory.requestFocus();
        } else {
            binding.etCategory.setEnabled(false);
            binding.etCategory.setText(mCategory[which]);
        }

    }

    //===============================
    // endregion // method
    //===============================

    //===============================
    // region // api
    //=============================

    /**
     * 업무분류 리스트를 가져온다.
     *
     * @param showCategory 로딩 후 카테고리 표시여부
     */
    public void requestWKS_05(boolean showCategory) {
        if (!ClsNetworkCheck.isConnectable(mContext)) {
            return;
        }

        if (showCategory)
            openLoadingBar();

        Http.wks(HttpBaseService.TYPE.GET, BaseConst.URL_HOST).WKS_05(
                BaseConst.URL_HOST,
                mSettings.Value.MEM_CID,
                mSettings.Value.MEM_01,
                mSettings.Value.TKN_03,
                mSettings.Value.MEM_CID
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
                        closeLoadingBar();
                        if (msg.what == 100) {
                            Response<WKS_Model> response = (Response<WKS_Model>) msg.obj;

                            if (response.isSuccessful()) {
                                if (response.body().Data.get(0).Validation) {
                                    bindingCategory(response.body().Data);

                                    if (showCategory)
                                        dropdownCategory();
                                }
                            }
                        }
                    }
                }.sendMessage(msg);
            }

            @Override
            public void onFailure(Call<WKS_Model> call, Throwable t) {
                call.cancel();
                closeLoadingBar();
            }
        });
    }

    /**
     * 업무분류 바인딩
     *
     * @param data
     */
    private void bindingCategory(ArrayList<WKS_VO> data) {
        mCategory = new String[data.size() + 1];

        mCategory[0] = getString(R.string.write_work_0);

        for (int i = 0; i < data.size(); i++) {
            mCategory[i + 1] = data.get(i).WKS_05;
        }
    }


    //=============================
    // endregion // api
    //===============================
}