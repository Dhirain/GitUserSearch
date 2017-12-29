package com.dhirain.myapplication.ui.history.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhirain.myapplication.R;
import com.github.vipulasri.timelineview.TimelineView;


/**
 * Created by DJ on 14-09-2017.
 */

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public TextView time_text, time_date;
    public ImageView time_image_type;
    public TimelineView timelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        timelineView = itemView.findViewById(R.id.time_marker);
        timelineView.initLine(viewType);
        time_text = (TextView) itemView.findViewById(R.id.time_text);
        time_date = (TextView) itemView.findViewById(R.id.time_date);
        time_image_type = (ImageView) itemView.findViewById(R.id.time_image);

    }
}
