package kr.hmit.afbis.ui.wks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.databinding.ItemWorkManagementListBinding;
import kr.hmit.afbis.databinding.ItemWorkManagementListEmployeeBinding;
import kr.hmit.afbis.model.vo.WKS_VO;

public class WorkManagementListEmployeeAdapter extends RecyclerView.Adapter {
    //=============================
    // region // variable
    //=============================
    private Context mContext;

    private ArrayList<EmployeeVO> mList;

    //=============================
    // endregion // variable
    //=============================


    public WorkManagementListEmployeeAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemWorkManagementListEmployeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;
        EmployeeVO vo = mList.get(position);

        finalHolder.binding.tvName.setText(vo.Name);

        switch (vo.State) {
            case UNREAD:
                finalHolder.binding.tvName.setSelected(false);
                finalHolder.binding.tvName.setActivated(false);
                break;
            case READ:
                finalHolder.binding.tvName.setSelected(true);
                finalHolder.binding.tvName.setActivated(false);
                break;
            case DONE:
                finalHolder.binding.tvName.setSelected(true);
                finalHolder.binding.tvName.setActivated(true);
                break;
        }
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
    public void update(ArrayList<EmployeeVO> list) {
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
        ItemWorkManagementListEmployeeBinding binding;


        public ViewHolder(ItemWorkManagementListEmployeeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    //=============================
    // endregion // ViewHolder
    //=============================
}
