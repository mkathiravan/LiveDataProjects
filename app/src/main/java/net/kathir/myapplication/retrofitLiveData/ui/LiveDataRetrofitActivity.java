package net.kathir.myapplication.retrofitLiveData.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import net.kathir.myapplication.Blog;
import net.kathir.myapplication.retrofitLiveData.viewmodel.BlogViewModel;
import net.kathir.myapplication.R;

import java.util.List;

public class LiveDataRetrofitActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private BlogViewModel blogViewModel;
    BlogAdapter mBlogAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.livedataretrofit_view);

        initializationViews();
        blogViewModel = ViewModelProviders.of(this).get(BlogViewModel.class);
        getPopularBlog();

        // lambda expression
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getPopularBlog();
        });
    }

    private void getPopularBlog() {

        swipeRefreshLayout.setRefreshing(true);
        blogViewModel.getAllBlog().observe(this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogList) {
                swipeRefreshLayout.setRefreshing(false);
                prepareRecyclerView(blogList);

            }
        });
    }


    private void prepareRecyclerView(List<Blog> blogList) {

        mBlogAdapter = new BlogAdapter(blogList);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mBlogAdapter);
        mBlogAdapter.notifyDataSetChanged();

    }

    private void initializationViews() {

        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.blogRecyclerView);

    }
}
