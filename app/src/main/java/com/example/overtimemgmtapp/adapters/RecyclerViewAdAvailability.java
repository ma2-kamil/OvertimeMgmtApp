package com.example.overtimemgmtapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.overtimemgmtapp.UserAvailability;
import com.example.overtimemgmtapp.R;
import com.example.overtimemgmtapp.classes.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdAvailability extends  RecyclerView.Adapter<RecyclerViewAdAvailability.viewHolder>{

    private Context context;
    private List<Schedule> mySchedule;

    public RecyclerViewAdAvailability(Context context, List<Schedule> mySchedule) {
        this.context = context;
        this.mySchedule = mySchedule;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.user_row_item, parent, false);

        final viewHolder viewHolders = new viewHolder(view);
        viewHolders.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UserAvailability.class);
                intent.putExtra("id", mySchedule.get(viewHolders.getAdapterPosition()).getId());
                intent.putExtra("Date", mySchedule.get(viewHolders.getAdapterPosition()).getDate());
                intent.putExtra("Start_Time", mySchedule.get(viewHolders.getAdapterPosition()).getFromtime());
                intent.putExtra("End_Time", mySchedule.get(viewHolders.getAdapterPosition()).getTotime());
                intent.putExtra("Employee_Name", mySchedule.get(viewHolders.getAdapterPosition()).getEmployee());

                context.startActivity(intent);

            }
        });



        return viewHolders;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        String date = (mySchedule.get(position).getDate());
        String start = (mySchedule.get(position).getFromtime());
        String end = (mySchedule.get(position).getTotime());
        String emp = (mySchedule.get(position).getEmployee());

        // DATE
        Date initDate = null;
        try {
            initDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yyyy");
        date = formatter.format(initDate);

        // Start Time
        Date initTime = null;
        try {
            initTime = new SimpleDateFormat("HH:mm:ss").parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatters = new SimpleDateFormat("HH:mm a");
        start = formatters.format(initTime);
        start = "Start: " + start;

        // Finish Time

        Date initTimes = null;
        try {
            initTimes = new SimpleDateFormat("HH:mm:ss").parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatterss = new SimpleDateFormat("HH:mm a");
        end = formatterss.format(initTimes);
        end = "Finish: " + end;

        // Setting the text to views
        holder.tv_date.setText(date);
        holder.tv_starttime.setText(start);
        holder.tv_endtime.setText(end);
        holder.tv_employee.setText(emp);

    }

    @Override
    public int getItemCount() {
        return mySchedule.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView tv_date;
        TextView tv_starttime;
        TextView tv_endtime;
        TextView tv_employee;
        LinearLayout view_container;

        public viewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.containeru);
            tv_date = itemView.findViewById(R.id.date_textu);
            tv_starttime = itemView.findViewById(R.id.start_timeu);
            tv_endtime = itemView.findViewById(R.id.end_timeu);
            tv_employee = itemView.findViewById(R.id.a_employeeu);



        }
    }

}
