package com.dhirain.gitrepo.manager

import com.dhirain.myapplication.model.RepoModel
import com.dhirain.myapplication.model.RepoNetworkResponse
import com.dhirain.myapplication.network.GitService
import com.dhirain.myapplication.utils.GenericCallback
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Dhirain Jain on 29-12-2017.
 */
object RepoManager {

    fun getRepos(search: String, map: HashMap<String, String>, genericCallback: GenericCallback<List<RepoModel>>) {

        GitService.instance().getRepo(search, map)
                .map { t: RepoNetworkResponse -> t.items }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data -> genericCallback.onRequestSuccess(data) },
                        { throwable -> genericCallback.onRequestFailure(throwable, "Newtork fail :-(") }

                )
    }
}

