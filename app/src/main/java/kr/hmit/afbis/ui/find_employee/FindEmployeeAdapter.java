package kr.hmit.afbis.ui.find_employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemFindEmployeeBinding;
import kr.hmit.afbis.model.vo.MEM_ReadVO;

public class FindEmployeeAdapter extends RecyclerView.Adapter {
    //===========================
    // variable
    //===========================
    private Context mContext;
    private ArrayList<MEM_ReadVO> mList;

    //===========================
    // initialize
    //===========================

    public FindEmployeeAdapter(Context context, ArrayList<MEM_ReadVO> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemFindEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MEM_ReadVO vo = mList.get(position);
        ViewHolder finalHolder = (ViewHolder) holder;

        finalHolder.binding.tvTextName.setText(vo.MEM_02);
        finalHolder.binding.tvTextPosition.setText(vo.MEM_32_NM);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 데이터 업데이트
     *
     * @param list
     */
    public void update(ArrayList<MEM_ReadVO> list) {
        mList = list;

        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFindEmployeeBinding binding;

        public ViewHolder(ItemFindEmployeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
