package com.dhirain.myapplication.ui.history.view;


import com.dhirain.myapplication.model.HistoryModel;

import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public interface TimeLineView {
    void showListState();

    void showEmptyState();

    void updateList(List<HistoryModel> totalNewsList);

    void showProgress();

    void hideProgress();
}
