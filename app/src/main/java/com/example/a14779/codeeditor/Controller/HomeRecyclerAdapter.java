package com.example.a14779.codeeditor.Controller;

import android.content.Context;
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
    private ReClickListener mClickListener;
    private String basePath;

    public HomeRecyclerAdapter(Context context, List<String> nameList) {
        this.context = context;
        this.nameList = nameList;
        basePath = context.getExternalFilesDir("").getPath();
    }

    @Override
    public HomeRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_page_list_item,
                parent,
                false),
                mClickListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String curName = nameList.get(position);
        File file = new File(basePath+"/"+curName);
        if (file.isDirectory()){
            holder.circleImageView.setImageResource(R.drawable.ic_folder_black_24dp);
        }
        else {
            switch (FileHelper.instance.getFileType(curName)) {
                case FileHelper.JAVA:
                    holder.circleImageView.setImageResource(R.drawable.java_icon);
                    break;
                case FileHelper.C:
                    holder.circleImageView.setImageResource(R.drawable.unknown_type_icon);
                    break;
                case FileHelper.CPP:
                    holder.circleImageView.setImageResource(R.drawable.cpp_icon);
                    break;
                default:
                    holder.circleImageView.setImageResource(R.drawable.unknown_type_icon);
                    break;

            }
        }
        holder.title.setText(curName);
        holder.size.setText(String.valueOf(FileHelper.getFileSize(curName, basePath)));
        holder.time.setText(TimeHelper.FormatTime(file.lastModified()));
        //holder.time.setText(String.valueOf(file.lastModified()));
    }

    public void setClickListener(ReClickListener clickListener){
        this.mClickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void addItem(String itemName){
        FileHelper.instance.saveFile(itemName, "", basePath);
        nameList.add(itemName);
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        private CircleImageView circleImageView;
        private TextView title;
        private TextView time;
        private TextView size;
        private ReClickListener clickListener;
        public MyViewHolder(View itemView,
                            ReClickListener clickListener) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.program_type_image);
            title = itemView.findViewById(R.id.program_name);
            time = itemView.findViewById(R.id.program_create_time);
            size = itemView.findViewById(R.id.program_size);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onLongClick(v, getAdapterPosition());
            return true;
        }
    }
}
