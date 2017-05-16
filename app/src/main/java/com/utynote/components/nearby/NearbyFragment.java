package com.utynote.components.nearby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utynote.R;
import com.utynote.components.ContentView;
import com.utynote.widgets.panel.SlidingUpPanelLayout;

import static com.utynote.utils.Preconditions.checkNotNull;

public class NearbyFragment extends Fragment {
    public static final String TAG = NearbyFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.nearby_container_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.nearby_pager);
        viewPager.setAdapter(new NearbyAdapter(getFragmentManager(), getContext()));

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.nearby_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();

        SlidingUpPanelLayout panel = getContentView().getSlidingPanel();
        panel.setParallaxOffset(getResources().getDimensionPixelSize(R.dimen.nearby_panel_paralax));
        panel.setTouchEnabled(true);
        panel.setAnchorPoint(0.6f);
        panel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }

    @NonNull
    private ContentView getContentView() {
        return checkNotNull((ContentView) getActivity());
    }
}
