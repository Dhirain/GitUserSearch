package com.dhirain.myapplication.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhirain.gitrepo.ui.web.WebActivity;
import com.dhirain.gitrepo.utils.Constants;
import com.dhirain.myapplication.R;
import com.dhirain.myapplication.model.RepoModel;
import com.dhirain.myapplication.utils.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> {
    private static final String TAG = "RepoAdapter";
    private List<RepoModel> repoModels;
    private Context context;
    private int lastPosition;

    public RepoAdapter(Context context) {
        this.context = context;
    }


    public void updateList(List<RepoModel> newRepo) {
        repoModels = new ArrayList<>();
        this.repoModels = newRepo;
        this.lastPosition = -1;
        notifyDataSetChanged();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_item, parent, false);
        return new RepoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        if (repoModels != null) {
            showCurrentItem(holder, repoModels.get(position));
            //setAnimation(holder.parent,position);
        }
    }

    private void showCurrentItem(final RepoViewHolder holder, final RepoModel repoModel) {
        holder.repo_id.setText("id:"+repoModel.getId());
        holder.repo_name.setText(repoModel.getLogin());
        holder.repo_url.setText(repoModel.getHtmlUrl());
        holder.repo_score.setText(repoModel.getScore());
        ImageUtils.setImage(context, repoModel.getAvatarUrl(), holder.repo_image, R.drawable.user);
        holder.repo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                Toast.makeText(context,"opening Link", Toast.LENGTH_SHORT).show();
                intent.putExtra(Constants.PROJECT_LINK,repoModel.getHtmlUrl());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (repoModels == null)
            return 0;
        else {
            return repoModels.size();
        }
    }


    public void swap(int adapterPosition, int adapterPosition1) {
        Collections.swap(repoModels, adapterPosition, adapterPosition1);
        notifyItemMoved(adapterPosition, adapterPosition1);
    }

    public void remove(int adapterPosition) {
        repoModels.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

}
