package com.dhirain.gitrepo.ui.web

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dhirain.gitrepo.utils.Constants
import com.dhirain.myapplication.R
import com.dhirain.myapplication.database.StoreHistoryAsync
import com.dhirain.myapplication.model.HistoryModel
import kotlinx.android.synthetic.main.activity_web.*
import murgency.customer.ui.base.BaseActivity
import java.util.*

class WebActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initUI()
    }

    override fun initUI() {
        val Url = intent.getStringExtra(Constants.PROJECT_LINK)
        intProgressbar()
        showProgress()
        web_view.setWebViewClient(MyBrowser())
        web_view.getSettings().setJavaScriptEnabled(true)
        web_view.loadUrl(Url)
        setTitleWithBackPress("Project Link")
        StoreHistoryAsync(this).execute(HistoryModel(Constants.CONTENT_CLICK,"Checked: "+Url, Calendar.getInstance().getTimeInMillis()))

    }

    override fun clickListener() {
    }

    override fun setupPresenter() {
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class MyBrowser : WebViewClient() {

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            super.onReceivedError(view, request, error)
            hideProgress()
        }

        override fun onPageFinished(view: WebView, url: String) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url)
            hideProgress()
          }
    }

}
