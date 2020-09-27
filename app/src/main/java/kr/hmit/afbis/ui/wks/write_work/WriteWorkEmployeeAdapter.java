package kr.hmit.afbis.ui.wks.write_work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemEmployeeListBinding;
import kr.hmit.afbis.model.vo.MEM_ReadVO;

public class WriteWorkEmployeeAdapter extends RecyclerView.Adapter {
    //=============================
    // region // variable
    //=============================
    private Context mContext;

    private ArrayList<MEM_ReadVO> mList;

    //=============================
    // endregion // variable
    //=============================
    public WriteWorkEmployeeAdapter(Context context, ArrayList<MEM_ReadVO> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEmployeeListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;
        MEM_ReadVO vo = mList.get(position);

        finalHolder.binding.tvName.setText(vo.MEM_02);
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
    public void update(ArrayList<MEM_ReadVO> list) {
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
        ItemEmployeeListBinding binding;

        public ViewHolder(ItemEmployeeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    //=============================
    // endregion // ViewHolder
    //=============================
}