package com.example.cvte.lottietest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/**
 * Created by CVTE on 2017/11/22.
 */

public class ShowListAdapter extends RecyclerView.Adapter<ShowListAdapter.StringViewHolder> {

    private Context mContext;
    private List<String> mNameList;

    public ShowListAdapter(Context context, List<String> nameList) {
        this.mContext = context;
        this.mNameList = nameList;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.string_item, parent, false);
        return new StringViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StringViewHolder holder, int position) {
        holder.textView.setText(mNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    class StringViewHolder extends  RecyclerView.ViewHolder {
        TextView textView;
        public StringViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.show_name_textView);
        }
    }
}
