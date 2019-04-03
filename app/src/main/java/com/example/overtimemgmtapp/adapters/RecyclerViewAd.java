package com.example.overtimemgmtapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.overtimemgmtapp.JobsActivity;
import com.example.overtimemgmtapp.R;
import com.example.overtimemgmtapp.classes.Jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecyclerViewAd extends  RecyclerView.Adapter<RecyclerViewAd.viewHolder>{

    private Context context;
    private List<Jobs> myJobs;

    public RecyclerViewAd(Context context, List<Jobs> myJobs) {
        this.context = context;
        this.myJobs = myJobs;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.job_row_item, parent, false);

        final viewHolder viewHolders = new viewHolder(view);
        viewHolders.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, JobsActivity.class);
                intent.putExtra("id", myJobs.get(viewHolders.getAdapterPosition()).getId());
                intent.putExtra("Shift_Title", myJobs.get(viewHolders.getAdapterPosition()).getShifttitle());
                intent.putExtra("Description", myJobs.get(viewHolders.getAdapterPosition()).getDescription());
                intent.putExtra("Hourly_Rate", myJobs.get(viewHolders.getAdapterPosition()).getHourlyrate());
                intent.putExtra("Date", myJobs.get(viewHolders.getAdapterPosition()).getDate());
                intent.putExtra("Start_Time", myJobs.get(viewHolders.getAdapterPosition()).getStarttime());
                intent.putExtra("End_Time", myJobs.get(viewHolders.getAdapterPosition()).getEndtime());
                intent.putExtra("Employee_Name", myJobs.get(viewHolders.getAdapterPosition()).getEmployee());

                context.startActivity(intent);

            }
        });



        return viewHolders;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        String date = (myJobs.get(position).getDate());
        String start = (myJobs.get(position).getStarttime());
        String end = (myJobs.get(position).getEndtime());
        String rate = (myJobs.get(position).getHourlyrate());

        // Formatting the data
        rate = "£" + rate + " per hour";

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
        holder.tv_jobtitle.setText(myJobs.get(position).getShifttitle());
        holder.tv_date.setText(date);
        holder.tv_starttime.setText(start);
        holder.tv_endtime.setText(end);
        holder.tv_hourlyrate.setText(rate);


    }

    @Override
    public int getItemCount() {
        return myJobs.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {


        TextView tv_jobtitle;
        TextView tv_date;
        TextView tv_starttime;
        TextView tv_endtime;
        TextView tv_hourlyrate;
        LinearLayout view_container;


        public viewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_jobtitle = itemView.findViewById(R.id.shift_heading);
            tv_date = itemView.findViewById(R.id.date_text);
            tv_starttime = itemView.findViewById(R.id.start_time);
            tv_endtime = itemView.findViewById(R.id.end_time);
            tv_hourlyrate = itemView.findViewById(R.id.rate);


        }
    }

}
