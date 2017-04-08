package io.memetic.alcohdex.data;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import io.memetic.alcohdex.feature.entries.model.BeerEntry;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         4/8/17
 */
public class EntryRepository {
    private static final EntryRepository ourInstance = new EntryRepository();
    private static final Collection<BeerEntry> entryList = new ArrayList<>();
    private static final ArrayMap<UUID, BeerEntry> entryMap = new ArrayMap<>();

    public static EntryRepository getInstance() {
        return ourInstance;
    }

    private EntryRepository() {
    }

    public void addEntry(BeerEntry beerEntry) {
        entryList.add(beerEntry);
        entryMap.put(beerEntry.mUuid.getUuid(), beerEntry);
    }

    public Collection<BeerEntry> getEntries() {
        return entryList;
    }
}
