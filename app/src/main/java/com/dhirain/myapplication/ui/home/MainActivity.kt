package com.dhirain.gitrepo.ui.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dhirain.gitrepo.utils.Constants
import com.dhirain.myapplication.R
import com.dhirain.myapplication.database.StoreHistoryAsync
import com.dhirain.myapplication.model.HistoryModel
import com.dhirain.myapplication.model.RepoModel
import com.dhirain.myapplication.ui.history.activity.TimeLineActivity
import com.dhirain.myapplication.ui.home.MainView
import com.dhirain.myapplication.ui.home.RepoAdapter
import com.dhirain.myapplication.ui.home.SwipeTouchHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import murgency.customer.ui.base.BaseActivity
import java.util.*

class MainActivity : BaseActivity(), MainView, NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainActivity";
    lateinit var mainPresenter: MainPresenter
    lateinit var repoAdapter: RepoAdapter
    val FILTER_REQUEST_CODE: Int = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        clickListener()
        initAdapter()
        setupPresenter()
        Log.d(TAG, "onCreate: great");
    }

    override fun initUI() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        intProgressbar()
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initAdapter() {
        main_recycler.hasFixedSize()
        val linearlayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        linearlayoutManager.orientation = LinearLayoutManager.VERTICAL
        main_recycler.layoutManager = linearlayoutManager
        repoAdapter = RepoAdapter(this)
        main_recycler.adapter = repoAdapter
        val mCallback = SwipeTouchHelper(repoAdapter)
        val swipeTouchHelper = ItemTouchHelper(mCallback)
        swipeTouchHelper.attachToRecyclerView(main_recycler)
    }


    override fun clickListener() {
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun setupPresenter() {
        mainPresenter = MainPresenter(this, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        var searchString: String? = null
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        var searchView: SearchView? = null
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
            searchView.queryHint = "Search Repos"
        }
        if (searchView != null) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.d(TAG, "onQueryTextSubmit: $query");
                    mainPresenter.search(query)
                    StoreHistoryAsync(this@MainActivity).execute(HistoryModel(Constants.CONTENT_SEARCH, "searched " + query, Calendar.getInstance().getTimeInMillis()))
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    //Give query to presenter
                    //searchString = newText
                    Log.d(TAG, "onQueryTextChange: $newText");
                    mainPresenter.search(newText)
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun updateList(repoModelList: MutableList<out RepoModel>?) {
        repoAdapter.updateList(repoModelList)
        if (repoModelList!!.isNotEmpty()) {
            showListState()
        } else {
            showEmptyState()
        }
    }

    private fun showEmptyState() {
        no_result_found.visibility = View.VISIBLE
        main_recycler.visibility = View.GONE
    }

    private fun showListState() {
        no_result_found.visibility = View.GONE
        main_recycler.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId
        if (id == R.id.history) {
            Log.d(TAG, "onNavigationItemSelected: history clicked");
            val intent = Intent(this@MainActivity, TimeLineActivity::class.java)
            startActivity(intent)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}
