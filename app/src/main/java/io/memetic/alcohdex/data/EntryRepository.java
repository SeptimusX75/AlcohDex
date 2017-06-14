package io.memetic.alcohdex.data;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.ParcelUuid;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Calendar;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.memetic.alcohdex.ComponentRegistry;
import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.memetic.alcohdex.feature.entries.realm.RealmBeerEntry;
import io.realm.Realm;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
@Module
public class EntryRepository {

    @Inject
    SharedPreferences mSharedPrefs;
    @Inject
    Gson mGson;

    private static final EntryRepository ourInstance = new EntryRepository();
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

    public void addEntry(Realm realm, BeerEntry beerEntry) {
        realm.executeTransactionAsync(it ->
                it.copyToRealmOrUpdate(createFromEntry(beerEntry))
        );
    }

    @NonNull
    private RealmBeerEntry createFromEntry(BeerEntry beerEntry) {
        UUID uuid = beerEntry.getUuid().getUuid();
        Uri uri = beerEntry.getImageUri();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(beerEntry.getTimestamp());
        RealmBeerEntry realmEntry = new RealmBeerEntry();
        realmEntry.setId(uuid.toString());
        realmEntry.setName(beerEntry.getName());
        realmEntry.setBrewery(beerEntry.getBrewery());
        realmEntry.setRating(beerEntry.getRating());
        realmEntry.setDate(calendar.getTime());
        if (uri != null) {
            realmEntry.setImageUri(uri.toString());
        }
        return realmEntry;
    }

    public BeerEntry getBeerEntryForKey(Realm realm, UUID uuid) {
        RealmBeerEntry entry = realm.where(RealmBeerEntry.class)
                .equalTo(RealmBeerEntry.primaryKey, uuid.toString()).findFirst();
        return createFromRealmEntry(entry);
    }

    @NonNull
    private BeerEntry createFromRealmEntry(RealmBeerEntry entry) {
        BeerEntry beerEntry = new BeerEntry();
        beerEntry.setUuid(ParcelUuid.fromString(entry.getId()));
        beerEntry.setName(entry.getName());
        beerEntry.setBrewery(entry.getBrewery());
        beerEntry.setRating(entry.getRating());
        beerEntry.setTimestamp(entry.getDate().getTime());
        return beerEntry;
    }
}
