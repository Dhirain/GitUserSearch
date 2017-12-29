package com.dhirain.myapplication.ui.history.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhirain.gitrepo.utils.Constants;
import com.dhirain.myapplication.R;
import com.dhirain.myapplication.model.HistoryModel;
import com.dhirain.myapplication.ui.history.view.TimeLineViewHolder;
import com.dhirain.myapplication.utils.DateUtil;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {
    private static final String TAG = "ListAdapter";
    private List<HistoryModel> musicModels;
    private Context context;


    public TimeLineAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<HistoryModel> newRepo) {
        musicModels = new ArrayList<>();
        this.musicModels = newRepo;
        notifyDataSetChanged();
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline_item, parent, false);
        return new TimeLineViewHolder(itemView, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        if (musicModels != null) {
            showCurrentItem(holder, musicModels.get(position), position);
        }
    }

    private void showCurrentItem(final TimeLineViewHolder holder, final HistoryModel musicModel, int pos) {
        holder.time_text.setText(musicModel.getText());
        holder.time_date.setText("on " + DateUtil.getUTCString(musicModel.getTime()));
        holder.timelineView.setMarker((pos == 0) ? context.getResources().getDrawable(R.drawable.ic_marker_active) : context.getResources().getDrawable(R.drawable.ic_marker_inactive));

        int drawableId;
        if (musicModel.getContentType() == Constants.CONTENT_SEARCH) {
            drawableId = R.drawable.ic_search;
        } else if (musicModel.getContentType() == Constants.CONTENT_CLICK) {
            drawableId = R.drawable.ic_branch;
        } else {
            drawableId = R.drawable.ic_search;
        }
        holder.time_image_type.setImageResource(drawableId);
    }

    @Override
    public int getItemCount() {
        if (musicModels == null)
            return 0;
        else {
            return musicModels.size();
        }
    }
}
