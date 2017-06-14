package io.memetic.alcohdex;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
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
import android.widget.TextView;

import com.airbnb.epoxy.SimpleEpoxyAdapter;

import javax.inject.Inject;

import io.memetic.alcohdex.data.EntryRepository;
import io.memetic.alcohdex.databinding.ActivityHomeBinding;
import io.memetic.alcohdex.feature.entries.AddEntryActivity;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.memetic.alcohdex.feature.entries.model.BeerListEntryBinder;
import io.memetic.alcohdex.feature.entries.realm.RealmBeerEntry;
import io.memetic.alcohdex.feature.entries.viewmodel.ListEntryVm;
import io.realm.OrderedCollectionChangeSet;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BeerListEntryBinder.Presenter {
    @Inject
    EntryRepository mRepository;

    private RecyclerView mRecyclerView;
    private SimpleEpoxyAdapter mAdapter;
    private ActivityHomeBinding mBinding;
    private TextView mEmptyListTextView;
    private HomeViewModel mHomeViewModel;
    private Realm mRealm;
    private RealmResults<RealmBeerEntry> mEntries;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHomeViewModel = new HomeViewModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        mBinding.setViewModel(mHomeViewModel);
        Toolbar toolbar = mBinding.appBarHomeBinding.toolbar;
        setSupportActionBar(toolbar);

        FloatingActionButton fab = mBinding.appBarHomeBinding.fab;
        fab.setOnClickListener((view) -> startAddEntryActivity(null));

        DrawerLayout drawer = mBinding.drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = mBinding.navView;
        navigationView.setNavigationItemSelectedListener(this);

        mAdapter = new SimpleEpoxyAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this, VERTICAL);

        mRecyclerView = mBinding.appBarHomeBinding.contentHomeBinding.recyclerView;
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(decoration);

        mEmptyListTextView = mBinding.appBarHomeBinding.contentHomeBinding.emptyListTextView;
        ComponentRegistry.getInstance().getAppComponent().inject(this);

        mRealm = Realm.getDefaultInstance();
        mEntries = mRealm.where(RealmBeerEntry.class).findAllSortedAsync(RealmBeerEntry.dateField);
        mEntries.addChangeListener(this::updateAdapter);
    }

    private void updateAdapter(RealmResults<RealmBeerEntry> entries, OrderedCollectionChangeSet changeSet) {
        if (changeSet == null) {
            for (RealmBeerEntry entry : entries) {
                addEntryToAdapter(entry);
            }
        } else {
            for (int i : changeSet.getChanges()) {
                RealmBeerEntry entry = entries.get(i);
                if (entry.isValid()) {
                    addEntryToAdapter(entry);
                } else {
                    mAdapter.removeModel(mAdapter.getModels().get(i));
                }
            }
        }
        mHomeViewModel.setEntryAvailable(!mEntries.isEmpty());
    }

    private void addEntryToAdapter(RealmBeerEntry entry) {
        BeerEntry beerEntry = new BeerEntry();
        beerEntry.setUuid(ParcelUuid.fromString(entry.getId()));
        beerEntry.setName(entry.getName());
        beerEntry.setBrewery(entry.getBrewery());
        beerEntry.setRating(entry.getRating());
        beerEntry.setTimestamp(entry.getDate().getTime());

        ListEntryVm vm = new ListEntryVm(beerEntry);
        mAdapter.addModels(new BeerListEntryBinder(vm, this));
    }

    @Override
    protected void onDestroy() {
        mBinding.drawerLayout.removeDrawerListener(mDrawerToggle);
        mRealm.close();
        super.onDestroy();
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

    @Override
    public void onEntrySelected(BeerEntry entry) {
        ParcelUuid uuid = entry.getUuid();
        startAddEntryActivity(uuid);
    }

    private void startAddEntryActivity(@Nullable ParcelUuid uuid) {
        Intent intent = new Intent(HomeActivity.this, AddEntryActivity.class);
        intent.putExtra(AddEntryActivity.EXTRA_ENTRY_ID, uuid);
        startActivity(intent);
    }
}
