package com.example.daggerpractise.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.engine.Resource;
import com.example.daggerpractise.models.Post;
import com.example.daggerpractise.net.AuthResource;
import com.example.daggerpractise.net.MainApi;
import com.example.daggerpractise.utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostFragmentViewModel extends ViewModel {
    private MainApi mainApi;
    public SessionManager sessionManager;

    private static final String TAG = "PostFragmentViewModel";
    private MediatorLiveData<AuthResource<List<Post>>> posts;

    @Inject
    public PostFragmentViewModel(MainApi mainApi, SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;

    }

    public LiveData<AuthResource<List<Post>>> observePosts() {
        if (posts == null) {
            posts = new MediatorLiveData<>();
            posts.setValue(AuthResource.loading((List<Post>) null));


            final LiveData<AuthResource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(

                    mainApi.getPostsFromUser(sessionManager.getUserLiveData().getValue().data.getId())

                            // instead of calling onError, do this
                            .onErrorReturn(new Function<Throwable, List<Post>>() {
                                @Override
                                public List<Post> apply(Throwable throwable) throws Exception {
                                    Log.e(TAG, "apply: ", throwable);
                                    Post post = new Post();
                                    post.setId(-1);
                                    ArrayList<Post> posts = new ArrayList<>();
                                    posts.add(post);
                                    return posts;
                                }
                            })

                            .map(new Function<List<Post>, AuthResource<List<Post>>>() {
                                @Override
                                public AuthResource<List<Post>> apply(List<Post> posts) throws Exception {
                                    if (posts.size() > 0) {
                                        if (posts.get(0).getId() == -1) {
                                            return AuthResource.error("Something went wrong", null);
                                        }
                                    }
                                    return AuthResource.success(posts);
                                }
                            })
                            .subscribeOn(Schedulers.io()));

            posts.addSource(source,value->{
                posts.setValue(value);
                posts.removeSource(source);
            });


        }
        return posts;
    }
}
