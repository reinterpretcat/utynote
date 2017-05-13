package com.utynote.components.search;

import android.support.annotation.NonNull;

final class SearchContract {

    public interface View {
        void showResults(@NonNull Iterable<SearchItemModel> results);
        void showError(@NonNull String description);
    }

    public interface Presenter {

        void attach(@NonNull View view);

        void detach();

        void search(@NonNull String term);
    }
}
