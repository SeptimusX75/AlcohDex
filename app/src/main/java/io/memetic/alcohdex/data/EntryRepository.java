package io.memetic.alcohdex.data;

import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxrelay2.PublishRelay;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.reactivex.Observable;

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
    private static final Collection<BeerEntry> entryList;
    private static final ArrayMap<UUID, BeerEntry> entryMap;

    private static final PublishRelay<BeerEntry> mEntryRelay;
    private static final PublishRelay<Collection<BeerEntry>> mEntryListRelay;

    private static final Observable<BeerEntry> sEntryObservable;

    static {
        entryList = new ArrayList<>();
        entryMap = new ArrayMap<>();
        mEntryRelay = PublishRelay.create();
        mEntryListRelay = PublishRelay.create();

        sEntryObservable = mEntryRelay.replay().autoConnect(0);
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
        entryList.add(beerEntry);
        entryMap.put(beerEntry.mUuid.getUuid(), beerEntry);

        mEntryRelay.accept(beerEntry);
        mEntryListRelay.accept(entryList);
    }

    public Collection<BeerEntry> getEntries() {
        return entryList;
    }

    public Observable<BeerEntry> getEntryObservable() {
        return sEntryObservable;
    }

    public Observable<Collection<BeerEntry>> getEntryListObservable() {
        return mEntryListRelay.replay();
    }

    public void commitEntries() {
        ((Runnable) () -> {
            SharedPreferences.Editor editor = mSharedPrefs.edit();
            editor.putString(
                    KEY_BEER_ENTRIES_LIST, mGson.toJson(entryList, sType));
            editor.apply();
        }).run();
    }

    public void loadEntries() {
        ((Runnable) () -> {
            String jsonString = mSharedPrefs.getString(KEY_BEER_ENTRIES_LIST, null);
            Collection<BeerEntry> storedEntries = mGson.fromJson(
                    jsonString,
                    sType
            );
            if (storedEntries == null) {
                return;
            }
            for (BeerEntry entry : storedEntries) {
                addEntry(entry);
            }
        }).run();
    }
}
