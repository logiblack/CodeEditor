package com.example.a14779.codeeditor.Controller;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a14779.codeeditor.R;

import java.io.File;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liangtao on 18-1-24.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<String> nameList;
    private View.OnClickListener mClickListener;
    private View.OnLongClickListener mLongClickListener;

    public HomeRecyclerAdapter(Context context, List<String> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    @Override
    public HomeRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_list_item,
                parent,
                false),
                mClickListener,
                mLongClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String basePath = context.getExternalFilesDir("").getPath();
        File file = new File(basePath+"/"+nameList.get(position));
        holder.circleImageView.setImageResource(R.drawable.java_icon);
        holder.title.setText(nameList.get(position));
        holder.size.setText(String.valueOf(FileHelper.getFileSize(nameList.get(position), basePath)));
        holder.time.setText(TimeHelper.FormatTime(file.lastModified()));
        //holder.time.setText(String.valueOf(file.lastModified()));
    }

    public void setOnItemClickListener(View.OnClickListener clickListener){
        this.mClickListener = clickListener;
    }

    public void setOnItemLongClickListener(View.OnLongClickListener longClickListener){
        mLongClickListener = longClickListener;
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleImageView;
        private TextView title;
        private TextView time;
        private TextView size;
        public MyViewHolder(View itemView,
                            View.OnClickListener clickListener,
                            View.OnLongClickListener longClickListener) {
            super(itemView);
            itemView.setOnClickListener(clickListener);
            itemView.setOnLongClickListener(longClickListener);
            circleImageView = itemView.findViewById(R.id.program_type_image);
            title = itemView.findViewById(R.id.program_name);
            time = itemView.findViewById(R.id.program_create_time);
            size = itemView.findViewById(R.id.program_size);
        }

    }
}
