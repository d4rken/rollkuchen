package de.rollkuchen.app.core;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import de.rollkuchen.app.ApplicationScope;
import io.reactivex.Observable;

/**
 * Created by darken (darken@darken.eu) on 07.03.2017.
 */
@ApplicationScope
public class ImageRepo {
    private final DatabaseReference databaseReference;
    private final StorageReference storageReference;
    private final FirebaseAuth authReference;

    @Inject
    public ImageRepo(FirebaseDatabase database, FirebaseStorage storage, FirebaseAuth authReference) {
        this.databaseReference = database.getReference("image_pool");
        this.storageReference = storage.getReference("image_pool");
        this.authReference = authReference;
    }

    public Observable<Map<String, Rollkuchen>> getRollkuchen() {
        return Observable.create(emitter -> databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Rollkuchen> rollkuchens = new HashMap<>();
                GenericTypeIndicator<Map<String, FBRollkuchen>> t = new GenericTypeIndicator<Map<String, FBRollkuchen>>() {};

                for (Map.Entry<String, FBRollkuchen> entry : dataSnapshot.getValue(t).entrySet()) {
                    Rollkuchen rollkuchen = new Rollkuchen(entry.getKey());
                    rollkuchens.put(rollkuchen.getIdentifier(), rollkuchen);
                    rollkuchen.setImage(storageReference.child(entry.getValue().getSrc()));
                    rollkuchen.setThumbnail(storageReference.child(entry.getValue().getSrct()));
                }
                emitter.onNext(rollkuchens);
                emitter.onComplete();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                emitter.onError(databaseError.toException());
            }
        }));
    }
}
