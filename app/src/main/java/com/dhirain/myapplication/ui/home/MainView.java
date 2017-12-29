package com.dhirain.myapplication.ui.home;


import com.dhirain.myapplication.model.RepoModel;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public interface MainView {
    void showProgress();

    void hideProgress();

    void updateList(@Nullable List<? extends RepoModel> RepoModelList);
}
