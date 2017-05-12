package com.utynote.components.search;

import android.support.annotation.NonNull;

import java.util.List;

final class SearchContract {

    public interface View {
        void showResults(@NonNull List<SearchItemModel> results);
        void showError(@NonNull String description);
    }

    public interface Presenter {

        void attach(@NonNull View view);

        void detach();

        void search(@NonNull String term);
    }
}
