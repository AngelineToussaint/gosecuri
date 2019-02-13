package fr.gosecuri.kernel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.google.gson.JsonParser;
import fr.gosecuri.model.Kit;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

abstract public class Firebase {
    private static FirebaseDatabase firebaseDatabase;

    public Firebase() {
        if (this.firebaseDatabase == null) {
            connection();
        }
    }

    private void connection() {
        try {
            FileInputStream serviceAccount = new FileInputStream(Property.getProperty("firebase.serviceAccount"));

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(Property.getProperty("firebase.url"))
                    .build();

            FirebaseApp.initializeApp(options);

            this.firebaseDatabase = FirebaseDatabase.getInstance();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getClassName() {
        String[] className = this.getClass().getName().split("\\.");

        return className[className.length - 1].toLowerCase();
    }

    public void store(String id, String key, Object value) {

        final CountDownLatch done = new CountDownLatch(1);

        DatabaseReference ref = firebaseDatabase.getReference(getClassName());

        try {

            ref.child(id).child(key).setValue(value, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    done.countDown();
                }

            });

            done.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String store(Object object) {

        String id = null;
        final CountDownLatch done = new CountDownLatch(1);

        DatabaseReference ref = firebaseDatabase.getReference(getClassName());
        try {

            DatabaseReference databaseReference = ref.push();

            id = databaseReference.getKey();

            databaseReference.setValue(object, new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    done.countDown();
                }

            });

            done.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void delete(String key) {

        final CountDownLatch latch = new CountDownLatch(1);

        DatabaseReference ref = firebaseDatabase.getReference(getClassName());

        try {

            ref.child(key).removeValue(new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    latch.countDown();
                }
            });

            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public DataSnapshot get(String key) {

        final DataSnapshot[] result = new DataSnapshot[1];
        final CountDownLatch done = new CountDownLatch(1);

        DatabaseReference ref = firebaseDatabase.getReference(getClassName());

        try {

            ref.child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    result[0] = dataSnapshot;

                    done.countDown();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());

                    done.countDown();
                }
            });

            done.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result[0];
    }
}
