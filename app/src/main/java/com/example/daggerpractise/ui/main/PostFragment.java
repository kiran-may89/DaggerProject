package com.example.daggerpractise.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerpractise.R;
import com.example.daggerpractise.di.factory.ViewModelProvidersFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {
    @Inject
    ViewModelProvidersFactory factory;

    private PostFragmentViewModel viewModel;
    private PostAdapter postAdapter;
    RecyclerView recyclerView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, factory).get(PostFragmentViewModel.class);
        recyclerView = view.findViewById(R.id.recycler_view);

        observeLiveData();
        initRecyclerView();
    }

    void initRecyclerView() {
        postAdapter = new PostAdapter();
        recyclerView.setAdapter(postAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        recyclerView.addItemDecoration(new RecyclerViewDecoration(8));

    }

    void observeLiveData() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), response -> {
            if (response.data != null){
                postAdapter.setPosts(response.data);
            }


        });

    }
}
