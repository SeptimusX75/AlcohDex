package io.memetic.alcohdex.entries.model;

import android.databinding.BaseObservable;
import android.net.Uri;

/**
 * TODO class description
 *
 * @author manuel.silva@mobileforming.com
 *         3/7/17
 */
public class BeerEntry extends BaseObservable {
    private String name;
    private String brewery;
    private Uri imageUri;
    private double rating;
    private long samples;
}
