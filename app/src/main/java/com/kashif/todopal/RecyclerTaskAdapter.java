package com.kashif.todopal;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TaskModel> taskModelArrayList;

    public RecyclerTaskAdapter(Context context, ArrayList<TaskModel> taskModelArrayList) {
        this.context = context;
        this.taskModelArrayList = taskModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTaskList.setText(taskModelArrayList.get(position).task);
        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to delete this Task ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (removeTask(taskModelArrayList.get(position))){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(context, "Please Try Again Later, something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }
        });
    }

    private boolean removeTask(TaskModel taskModel) {
        return taskModelArrayList.remove(taskModel);
    }

    @Override
    public int getItemCount() {
        return taskModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTaskList, btnDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskList = itemView.findViewById(R.id.tvTaskList);
            btnDel = (TextView) itemView.findViewById(R.id.btnDel);
        }
    }

}