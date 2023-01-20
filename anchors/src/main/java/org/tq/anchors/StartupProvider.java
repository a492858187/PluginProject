package org.tq.anchors;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class StartupProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        try{
            List<StartUp<?>> startups = StartupInitializer.discoverAndInitialize(getContext(), getClass().getName());
            new StartUpManager.Builder()
                    .addAllStartup(startups)
                    .build(getContext())
                    .start()
                    .await();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new IllegalStateException("Not allowed.");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        throw new IllegalStateException("Not allowed.");
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        throw new IllegalStateException("Not allowed.");
    }
}
