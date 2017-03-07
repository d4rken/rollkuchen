package de.rollkuchen.app.core;


import com.google.firebase.storage.StorageReference;

public class Rollkuchen {
    private final String uuid;
    private StorageReference image;
    private StorageReference thumbnail;

    public Rollkuchen(String uuid) {
        this.uuid = uuid;
    }

    public String getIdentifier() {
        return uuid;
    }

    public StorageReference getImage() {
        return image;
    }

    public void setImage(StorageReference image) {
        this.image = image;
    }

    public StorageReference getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(StorageReference thumbnail) {
        this.thumbnail = thumbnail;
    }

}
