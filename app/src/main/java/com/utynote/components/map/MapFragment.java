package com.utynote.components.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.R;

public class MapFragment extends Fragment {
    public static final String TAG = MapFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.map_container_view, container, false);
    }
}
