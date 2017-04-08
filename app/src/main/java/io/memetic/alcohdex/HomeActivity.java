package io.memetic.alcohdex;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.SimpleEpoxyAdapter;

import java.util.Collection;

import javax.inject.Inject;

import io.memetic.alcohdex.data.EntryRepository;
import io.memetic.alcohdex.databinding.ActivityHomeBinding;
import io.memetic.alcohdex.feature.entries.AddEntryActivity;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.memetic.alcohdex.feature.entries.model.BeerListEntryBinder;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Inject
    EntryRepository mRepository;

    private RecyclerView mRecyclerView;
    private SimpleEpoxyAdapter mAdapter;
    private ActivityHomeBinding mBinding;
    private TextView mEmptyListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        Toolbar toolbar = mBinding.appBarHomeBinding.toolbar;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = mBinding.appBarHomeBinding.fab;
        fab.setOnClickListener((view) -> {
            Intent intent = new Intent(HomeActivity.this, AddEntryActivity.class);
            startActivity(intent);
        });

        //                Snackbar.make(view, "Replace with your own action",
        //                        Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();

        DrawerLayout drawer = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        mAdapter = new SimpleEpoxyAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        mRecyclerView = mBinding.appBarHomeBinding.contentHomeBinding.recyclerView;
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);

        mEmptyListTextView = mBinding.appBarHomeBinding.contentHomeBinding.emptyListTextView;
        ((App) getApplication()).getComponentRegistry().repoComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.removeAllModels();

        Collection<BeerEntry> entries = mRepository.getEntries();
        boolean noEntries = entries.isEmpty();
        mEmptyListTextView.setVisibility(
                noEntries ? View.VISIBLE : View.GONE
        );

        if (noEntries) return;

        for (BeerEntry entry : entries) {
            mAdapter.addModels(new BeerListEntryBinder(entry));
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
