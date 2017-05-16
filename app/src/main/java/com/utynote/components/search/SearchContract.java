package com.utynote.components.search;

import android.support.annotation.NonNull;

final class SearchContract {

    public interface View {
        void render(@NonNull SearchViewModel.Abstract model);
    }

    public interface Presenter {

        void attach(@NonNull View view);

        void detach();

        void search(@NonNull String term);
    }
}
