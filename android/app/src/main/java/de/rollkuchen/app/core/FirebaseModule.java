package de.rollkuchen.app.core;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by darken (darken@darken.eu) on 07.03.2017.
 */
@Module
public class FirebaseModule {

    @Provides
    FirebaseDatabase database() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    FirebaseStorage storage() {
        return FirebaseStorage.getInstance();
    }

    @Provides
    FirebaseAuth auth() {
        return FirebaseAuth.getInstance();
    }
}
