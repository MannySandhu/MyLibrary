package com.sandhu.manny.mylibrary.api.pojos;

import com.google.gson.annotations.SerializedName;
import com.sandhu.manny.mylibrary.api.pojos.VolumeInfo;

public class Items {

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo = new VolumeInfo();

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
