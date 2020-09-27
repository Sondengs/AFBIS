package kr.hmit.afbis.ui.product_info.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemProductionInfoMainListBinding;
import kr.hmit.afbis.databinding.ItemWorkManagementListBinding;
import kr.hmit.afbis.model.vo.WKS_VO;
import kr.hmit.afbis.ui.product_info.model.ProductionInfoVO;
import kr.hmit.afbis.ui.work_management.adapter.WorkManagementListAdapter;
import kr.hmit.afbis.ui.work_management.adapter.WorkManagementListEmployeeAdapter;
import kr.hmit.afbis.ui.work_management.model.EmployeeVO;

public class ProductionInfoListAdapter extends RecyclerView.Adapter {
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

    private ArrayList<ProductionInfoVO> mList;

    //=============================
    // endregion // variable
    //=============================


    public ProductionInfoListAdapter(Context context, ArrayList<ProductionInfoVO> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProductionInfoMainListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;
        ProductionInfoVO vo = mList.get(position);

        finalHolder.binding.tvProductName.setText(vo.DAH_02);
        finalHolder.binding.tvSizeValue.setText(vo.DAH_14);
        finalHolder.binding.tvStockCount.setText(String.valueOf(vo.OOK_07));
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
    public void update(ArrayList<ProductionInfoVO> list) {
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
        ItemProductionInfoMainListBinding binding;


        public ViewHolder(ItemProductionInfoMainListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

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
