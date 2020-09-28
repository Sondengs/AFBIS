package kr.hmit.afbis.ui.release.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemReleaseMainListBinding;
import kr.hmit.afbis.ui.release.model.ReleaseVO;
import kr.hmit.afbis.ui.worker_code.adapter.WorkerCodeMainAdapter;

public class ReleaseListAdapter extends RecyclerView.Adapter {
    //=================================
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
    //=================================

    //=============================
    // region // variable
    //=============================

    private Context mContext;

    private ArrayList<ReleaseVO> mList;

    //=============================
    // endregion // variable
    //=============================

    public ReleaseListAdapter(Context context, ArrayList<ReleaseVO> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemReleaseMainListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;
        ReleaseVO vo = mList.get(position);

        finalHolder.binding.tvOrderNumber.setText(vo.REQ_01);
        finalHolder.binding.tvOrderDate.setText(vo.REQ_02);
        finalHolder.binding.tvOrderName.setText(vo.REQ_03_NM);
        finalHolder.binding.tvOrderProduct.setText(vo.REQ_04);
        finalHolder.binding.tvQuantity.setText(String.valueOf(vo.REQ_06));
        finalHolder.binding.tvPrice.setText(String.valueOf(vo.REQ_05));
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
    public void update(ArrayList<ReleaseVO> list) {
        mList = list;

        notifyDataSetChanged();
    }


    //=============================
    // region // ViewHolder
    //=============================
    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReleaseMainListBinding binding;

        public ViewHolder(ItemReleaseMainListBinding binding) {
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
