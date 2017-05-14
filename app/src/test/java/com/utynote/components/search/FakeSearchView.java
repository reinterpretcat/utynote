package com.utynote.components.search;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class FakeSearchView implements SearchContract.View {

    @NonNull public final List<Iterable<SearchItemModel>> results = new ArrayList<>();
    @NonNull public final List<String> errors = new ArrayList<>();

    @Override
    public void showResults(@NonNull Iterable<SearchItemModel> results) {
        this.results.add(results);
    }

    @Override
    public void showError(@NonNull String description) {
        this.errors.add(description);
    }
}
