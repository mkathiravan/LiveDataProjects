package net.kathir.myapplication.retrofitLiveData.ui;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import net.kathir.myapplication.Blog;
import net.kathir.myapplication.R;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = BlogAdapter.class.getSimpleName();

    private List<Blog> mBlogList;

    public BlogAdapter(List<Blog> blogList)
    {
        mBlogList = blogList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

        holder.onBind(position);

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {

        if(mBlogList != null && mBlogList.size() > 0)
        {
            return mBlogList.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolder extends BaseViewHolder
    {
        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvLink;

        public ViewHolder(View itemView)
        {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvLink = itemView.findViewById(R.id.tvLink);
        }

        @Override
        protected void clear() {
            ivThumbnail.setImageDrawable(null);
            tvTitle.setText("");
            tvLink.setText("");

        }
        public void onBind(int position)
        {
           super.onBind(position);
           final Blog mBlog = mBlogList.get(position);
           if(mBlog.getThumbnail() != null)
           {
               Glide.with(itemView.getContext()).load(mBlog.getThumbnail()).into(ivThumbnail);
           }

           if(mBlog.getTitle() != null)
           {
               tvTitle.setText(mBlog.getTitle());
           }

           if(mBlog.getDescription() != null)
           {
               tvDescription.setText(mBlog.getDescription());
           }
            if (mBlog.getLink() != null)
            {
                tvLink.setText(mBlog.getLink());
            }

            tvLink.setOnClickListener( view -> {

                if(mBlog.getLink() != null)
                {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(mBlog.getLink()));
                        itemView.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: Image url is not correct");
                    }
                }
            });



        }
    }
}
