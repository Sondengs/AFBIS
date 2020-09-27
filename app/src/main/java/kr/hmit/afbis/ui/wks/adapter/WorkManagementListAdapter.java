package kr.hmit.afbis.ui.wks.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemWorkManagementListBinding;
import kr.hmit.afbis.model.vo.WKS_VO;
import kr.hmit.afbis.ui.wks.model.EmployeeVO;

public class WorkManagementListAdapter extends RecyclerView.Adapter {
    //============================
    // region // interface
    //=================================
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    //=================================
    // endregion // interface
    //============================

    //=============================
    // region // variable
    //=============================
    private Context mContext;

    private ArrayList<WKS_VO> mList;

    //=============================
    // endregion // variable
    //=============================


    public WorkManagementListAdapter(Context context, ArrayList<WKS_VO> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemWorkManagementListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;
        WKS_VO vo = mList.get(position);

        finalHolder.binding.tvWorkNo.setText(vo.WKS_01);
        finalHolder.binding.tvCategory.setText(vo.WKS_05);
        finalHolder.binding.tvContents.setText(vo.WKS_04);
        finalHolder.binding.tvRequestDate.setText(vo.WKS_02);
        finalHolder.binding.tvCompleteDate.setText(vo.WKS_06);

        ArrayList<EmployeeVO> employees = getEmployee(vo);
        finalHolder.mAdapter.update(employees);
        finalHolder.binding.tvState.setText(getState(employees));
    }

    private String getState(ArrayList<EmployeeVO> employees) {
        String state = "처리중";

        int readCount = 0;
        int doneCount = 0;

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).State.equals(EmployeeVO.StateWork.DONE))
                doneCount++;
            else if (employees.get(i).State.equals(EmployeeVO.StateWork.READ))
                readCount++;
        }

        if (employees.size() == doneCount)
            state = "완료";
        else if (readCount == 0)
            state = "대기";

        return state;
    }

    private ArrayList<EmployeeVO> getEmployee(WKS_VO vo) {
        ArrayList<EmployeeVO> list = new ArrayList<>();

        if (!TextUtils.isEmpty(vo.WKS_1001_NM)) {
            list.add(new EmployeeVO(vo.WKS_1001_NM, vo.WKS_1001, EmployeeVO.StateWork.from(vo.WKS_1002)));
        }

        if (!TextUtils.isEmpty(vo.WKS_1101_NM)) {
            list.add(new EmployeeVO(vo.WKS_1101_NM, vo.WKS_1101, EmployeeVO.StateWork.from(vo.WKS_1102)));
        }

        if (!TextUtils.isEmpty(vo.WKS_1201_NM)) {
            list.add(new EmployeeVO(vo.WKS_1201_NM, vo.WKS_1201, EmployeeVO.StateWork.from(vo.WKS_1202)));
        }

        if (!TextUtils.isEmpty(vo.WKS_1301_NM)) {
            list.add(new EmployeeVO(vo.WKS_1301_NM, vo.WKS_1301, EmployeeVO.StateWork.from(vo.WKS_1302)));
        }

        return list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //=============================
    // region // methods
    //=============================

    /**
     * 데이터를 업데이트 한다.
     *
     * @param list
     */
    public void update(ArrayList<WKS_VO> list) {
        mList = list;
        notifyDataSetChanged();
    }

    //=============================
    // endregion // variable
    //=============================


    //=============================
    // region // ViewHolder
    //=============================
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemWorkManagementListBinding binding;
        WorkManagementListEmployeeAdapter mAdapter;

        public ViewHolder(ItemWorkManagementListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            this.mAdapter = new WorkManagementListEmployeeAdapter(mContext);
            this.binding.recyclerView.setAdapter(mAdapter);

            itemView.setOnClickListener(v -> {
                int pos = getAdapterPosition();

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v, pos);
                }
            });
        }
    }
    //=============================
    // endregion // ViewHolder
    //=============================
}
