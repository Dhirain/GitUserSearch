package com.dhirain.gitrepo.ui.home

import android.content.Context
import android.util.Log
import com.dhirain.gitrepo.manager.RepoManager
import com.dhirain.myapplication.model.RepoModel
import com.dhirain.myapplication.ui.home.MainView
import com.dhirain.myapplication.utils.GenericCallback

/**
 * Created by Dhirain Jain on 29-12-2017.
 */
class MainPresenter(val context: Context, val mainView: MainView) {
    private val TAG = "MainPresenter";
    var searchString:String? = "Dhirain"
    var filterHash:HashMap<String,String> = HashMap()
    init {
        mainView.showProgress()
        getDatafromNetwork();
    }

    fun getDatafromNetwork() {
        //
        if(searchString== null || searchString.equals(""))
            searchString = "Dhirain"

        filterHash.put("sort","followers")

        RepoManager.getRepos(searchString!!, filterHash, object : GenericCallback<List<RepoModel>> {
            override fun onRequestSuccess(objectToReturn: List<RepoModel>?) {
                Log.d(TAG, "onRequestSuccess: " + objectToReturn.toString())
                mainView.updateList(objectToReturn)
                mainView.hideProgress()
            }
            override fun onRequestFailure(error: Throwable?, errorMessage: String?) {
                Log.d(TAG, "onRequestFailure: $errorMessage");
                //Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
                mainView.hideProgress()
                error?.printStackTrace()
            }
        });
    }

    fun search(searchString: String?) {
        if(searchString!=null && searchString.length>2) {
            this.searchString = searchString
            getDatafromNetwork()
        }
    }


}