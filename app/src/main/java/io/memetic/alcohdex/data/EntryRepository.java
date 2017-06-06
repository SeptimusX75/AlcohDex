package io.memetic.alcohdex.data;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxrelay2.PublishRelay;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.memetic.alcohdex.feature.entries.realm.RealmBeerEntry;
import io.reactivex.Observable;
import io.realm.Realm;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
@Module
public class EntryRepository {

    public static final String KEY_BEER_ENTRIES_LIST = "key_beer_entries_list";

    @Inject
    SharedPreferences mSharedPrefs;
    @Inject
    Gson mGson;

    private static final EntryRepository ourInstance = new EntryRepository();

    private static final Type sType;
    private static final ArrayList<BeerEntry> sEntryList;
    private static final ArrayMap<UUID, BeerEntry> sEntryMap;

    private static final PublishRelay<BeerEntry> sEntryRelay;
    private static final Observable<BeerEntry> sEntryObservable;

    static {
        sEntryList = new ArrayList<>();
        sEntryMap = new ArrayMap<>();
        sEntryRelay = PublishRelay.create();

        sEntryObservable = sEntryRelay.replay().autoConnect(0);
        sType = new TypeToken<ArrayList<BeerEntry>>() {
        }.getType();
    }

    {
        ComponentRegistry.getInstance().getAppComponent().inject(this);
    }

    @Singleton
    @Provides
    public static EntryRepository getInstance() {
        return ourInstance;
    }

    private EntryRepository() {
    }

    public void addEntry(BeerEntry beerEntry) {

        UUID uuid = beerEntry.getUuid().getUuid();

        RealmBeerEntry realmEntry = new RealmBeerEntry();
        realmEntry.setId(uuid.toString());
        realmEntry.setName(beerEntry.getName());
        realmEntry.setBrewery(beerEntry.getBrewery());
        realmEntry.setRating(beerEntry.getRating());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beerEntry.getTimestamp());
        realmEntry.setDate(calendar.getTime());

        Uri uri = beerEntry.getImageUri();
        if (uri != null) {
            realmEntry.setImageUri(uri.toString());
        }

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(it -> realm.copyToRealm(realmEntry));
    }

    @Nullable
    public BeerEntry getEntryById(UUID uuid) {
        return sEntryMap.get(uuid);
    }

    public Collection<BeerEntry> getEntries() {
        return sEntryList;
    }

    public Observable<BeerEntry> getEntryObservable() {
        return sEntryObservable;
    }
}
