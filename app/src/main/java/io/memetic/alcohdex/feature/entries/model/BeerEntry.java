package io.memetic.alcohdex.feature.entries.model;

import android.databinding.Bindable;
import android.net.Uri;
import android.os.ParcelUuid;

import io.memetic.alcohdex.BR;
import io.memetic.alcohdex.core.BaseModel;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/7/17
 */
public class BeerEntry extends BaseModel {
    private ParcelUuid mUuid;

    private String mName;
    private String mBrewery;
    private Uri mImageUri;
    private float mRating;
    private long mTimestamp;
    
    public ParcelUuid getUuid() {
        return mUuid;
    }

    public void setUuid(ParcelUuid uuid) {
        mUuid = uuid;
    }

    @Bindable
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getBrewery() {
        return mBrewery;
    }

    public void setBrewery(String brewery) {
        this.mBrewery = brewery;
        notifyPropertyChanged(BR.brewery);
    }

    @Bindable
    public Uri getImageUri() {
        return mImageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.mImageUri = imageUri;
        notifyPropertyChanged(BR.imageUri);
    }

    @Bindable
    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
        this.mRating = rating;
        notifyPropertyChanged(BR.rating);
    }

    @Bindable
    public long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(long timestamp) {
        this.mTimestamp = timestamp;
        notifyPropertyChanged(BR.timestamp);
    }
}
