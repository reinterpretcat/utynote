package com.utynote.components.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.R;
import com.utynote.app.ContentView;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.List;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchFragment extends Fragment {
    public static final String TAG = SearchFragment.class.getSimpleName();

    @NonNull private final SearchAdapter mAdapter = new SearchAdapter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        SlidingUpPanelLayout panel = getContentView().getSlidingPanel();
        Toolbar toolbar = getContentView().getToolbar();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float anchor = (metrics.heightPixels - toolbar.getMeasuredHeight()) / (float) metrics.heightPixels;

        panel.setParallaxOffset(0);
        panel.setTouchEnabled(false);
        panel.setAnchorPoint(anchor);
        panel.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.search_container_view, container, false);

        recyclerView.setAdapter(mAdapter);

        addFakeData();

        return recyclerView;
    }

    @NonNull
    private ContentView getContentView() {
        return checkNotNull((ContentView) getActivity());
    }

    // TODO remove
    private void addFakeData() {
        List<SearchItemModel> searchItems = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            searchItems.add(SearchItemModel.getBuilder().withTitle(new SpannableString("Item " + i)).build());
        }
        mAdapter.setData(searchItems);
    }
}
