package com.dhirain.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class RepoNetworkResponse {

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<RepoModel> items;

    public List<RepoModel> getItems() {
        return items;
    }
}
