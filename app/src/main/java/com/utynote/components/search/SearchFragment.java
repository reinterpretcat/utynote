package com.utynote.components.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.app.ContentView;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchFragment extends Fragment {
    public static final String TAG = SearchFragment.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SlidingUpPanelLayout panel = getContentView().getSlidingPanel();
        Toolbar toolbar = getContentView().getToolbar();
        float anchor = (panel.getMeasuredHeight() - toolbar.getMeasuredHeight()) / (float) panel.getMeasuredHeight();

        panel.setParallaxOffset(0);
        panel.setTouchEnabled(false);
        panel.setAnchorPoint(anchor);
        panel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        return null;
    }

    @NonNull
    private ContentView getContentView() {
        return checkNotNull((ContentView) getActivity());
    }
}
