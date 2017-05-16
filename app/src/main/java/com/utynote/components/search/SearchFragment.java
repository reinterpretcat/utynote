package com.utynote.components.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.R;
import com.utynote.components.ContentView;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import javax.inject.Inject;

import static com.utynote.utils.Preconditions.checkNotNull;

public class SearchFragment extends Fragment {

    public static final String TAG = SearchFragment.class.getSimpleName();

    @Nullable @Inject SearchPresenter presenter;
    @NonNull private final SearchAdapter adapter = new SearchAdapter();

    private final SearchContract.View searchView = new SearchContract.View() {
        @Override
        public void showResults(@NonNull Iterable<SearchItemModel> results) {
            adapter.setData(results);
        }

        @Override
        public void showError(@NonNull String description) {
            // TODO
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().attach(searchView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getPresenter().detach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.search_container_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((RecyclerView) view).setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();

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

    public void onSearchTerm(@NonNull CharSequence term) {
        getPresenter().search(term.toString());
    }

    @NonNull
    private ContentView getContentView() {
        return checkNotNull((ContentView) getActivity());
    }

    @NonNull
    private SearchContract.Presenter getPresenter() {
        return checkNotNull(presenter, "Presenter is not injected.");
    }
}
