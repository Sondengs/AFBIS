package kr.hmit.afbis.ui.drawer_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hmit.afbis.R;

public class SubMenuAdapter extends RecyclerView.Adapter {
    //=================================
    // final
    //=================================


    //=================================
    // variable
    //=================================
    private Context mContext;
    private ArrayList<String> mList;

    //=================================
    // interface
    //=================================
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //=================================
    // initialize
    //=================================
    public SubMenuAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_sub_menu, parent, false);
        RecyclerView.ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder finalHolder = (ViewHolder) holder;

        finalHolder.tvSubMenu.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void update(ArrayList<String> subMenu) {
        mList = subMenu;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubMenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubMenu = itemView.findViewById(R.id.tvSubMenu);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();

                Toast.makeText(mContext, "SubMenu Click - " + tvSubMenu.getText().toString(), Toast.LENGTH_SHORT).show();

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, pos);
                }
            });
        }
    }
}
