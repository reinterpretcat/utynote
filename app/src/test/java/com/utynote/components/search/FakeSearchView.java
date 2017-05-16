package com.utynote.components.search;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

final class FakeSearchView implements SearchContract.View {

    @NonNull public final List<SearchViewModel.Data> results = new ArrayList<>();
    @NonNull public final List<SearchViewModel.Error> errors = new ArrayList<>();
    @NonNull public final List<SearchViewModel.Busy> busy = new ArrayList<>();


    @Override
    public void render(@NonNull SearchViewModel.Abstract model) {
        model.accept(new SearchViewModel.Visitor() {
            @Override
            public void visit(@NonNull SearchViewModel.Busy model) {
                busy.add(model);
            }

            @Override
            public void visit(@NonNull SearchViewModel.Error model) {
                errors.add(model);
            }

            @Override
            public void visit(@NonNull SearchViewModel.Data model) {
                results.add(model);
            }
        });
    }
}
