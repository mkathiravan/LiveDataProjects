package net.kathir.myapplication.retrofitLiveData.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import net.kathir.myapplication.Blog;
import net.kathir.myapplication.retrofitLiveData.model.BlogRepository;

import java.util.List;

public class BlogViewModel extends AndroidViewModel {

    private BlogRepository mBlogRepository;

    public BlogViewModel(Application application) {
        super(application);
        mBlogRepository = new BlogRepository(application);
    }

    public LiveData<List<Blog>> getAllBlog()
    {
        return mBlogRepository.getMutableLiveData();
    }

}
