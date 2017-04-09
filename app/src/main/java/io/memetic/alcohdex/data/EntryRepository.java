package io.memetic.alcohdex.data;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import io.memetic.alcohdex.feature.entries.model.BeerEntry;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class EntryRepository {
    private static final EntryRepository ourInstance = new EntryRepository();

    private static final Collection<BeerEntry> entryList;
    private static final ArrayMap<UUID, BeerEntry> entryMap;

    private static final Subject<BeerEntry> mEntryObservable;
    private static final Subject<Collection<BeerEntry>> mEntryListObservable;

    static {
        entryList = new ArrayList<>();
        entryMap = new ArrayMap<>();
        mEntryObservable = PublishSubject.create();
        mEntryListObservable = PublishSubject.create();
    }

    public static EntryRepository getInstance() {
        return ourInstance;
    }

    private EntryRepository() {
    }

    public void addEntry(BeerEntry beerEntry) {
        entryList.add(beerEntry);
        entryMap.put(beerEntry.mUuid.getUuid(), beerEntry);

        mEntryObservable.onNext(beerEntry);
        mEntryListObservable.onNext(entryList);
    }

    public Collection<BeerEntry> getEntries() {
        return entryList;
    }

    public Observable<BeerEntry> getEntryObservable() {
        return mEntryObservable;
    }

    public Observable<Collection<BeerEntry>> getEntryListObservable() {
        return mEntryListObservable;
    }
}
