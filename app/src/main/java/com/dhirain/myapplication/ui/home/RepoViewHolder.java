package com.dhirain.myapplication.ui.home;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhirain.myapplication.R;


/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder {
    public CardView repo_card;
    public ImageView repo_image;
    public TextView repo_name, repo_id, repo_url;
    public AppCompatTextView repo_score;

    public RepoViewHolder(View itemView) {
        super(itemView);
        repo_card = itemView.findViewById(R.id.repo_card);
        repo_image = itemView.findViewById(R.id.repo_image);
        repo_name = itemView.findViewById(R.id.repo_name);
        repo_id = itemView.findViewById(R.id.repo_id);
        repo_url = itemView.findViewById(R.id.repo_url);
        repo_score = itemView.findViewById(R.id.repo_score);
        }
}
