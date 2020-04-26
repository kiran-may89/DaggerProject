package com.example.daggerpractise.ui.main;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private int verticalOffset;

    public RecyclerViewDecoration(int verticalOffset) {
        this.verticalOffset = verticalOffset;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        outRect.bottom = verticalOffset;
    }
}
