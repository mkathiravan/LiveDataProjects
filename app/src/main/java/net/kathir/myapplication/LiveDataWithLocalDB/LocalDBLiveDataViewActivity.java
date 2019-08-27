package net.kathir.myapplication.LiveDataWithLocalDB;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.kathir.myapplication.MainActivity;
import net.kathir.myapplication.R;

import java.util.Date;
import java.util.List;

public class LocalDBLiveDataViewActivity extends AppCompatActivity {

    private NameModelAdapter mNameAdapter;
    private NamesViewModel namesViewModel;
    private List<NameModel> mNameList;
    FloatingActionButton fab;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localdb_view);
        fab = findViewById(R.id.fab);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        namesViewModel = ViewModelProviders.of(LocalDBLiveDataViewActivity.this).get(NamesViewModel.class);
        final Observer<List<NameModel>> nameObserver = new Observer<List<NameModel>>() {
            @Override
            public void onChanged(List<NameModel> updatedList) {

                if (mNameList == null) {
                    mNameList = updatedList;
                    mNameAdapter = new NameModelAdapter();
                    recyclerView.setAdapter(mNameAdapter);
                }
                else
                {
                    DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                        @Override
                        public int getOldListSize() {
                            return mNameList.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return updatedList.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return mNameList.get(oldItemPosition).mId == updatedList.get(newItemPosition).mId;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            NameModel nameModel = mNameList.get(oldItemPosition);
                            NameModel newnameModel = updatedList.get(newItemPosition);
                            return nameModel.equals(newnameModel);
                        }
                    });

                    result.dispatchUpdatesTo(mNameAdapter);

                }

            }
        };

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText inUrl = new EditText(LocalDBLiveDataViewActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(LocalDBLiveDataViewActivity.this)
                        .setTitle("New favourite")
                        .setMessage("Add a url link below")
                        .setView(inUrl)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(inUrl.getText());
                                long date = (new Date()).getTime();

                                namesViewModel.addNames(url, date);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

        namesViewModel.getNames().observe(this, nameObserver);
    }

    public class NameModelAdapter extends RecyclerView.Adapter<NameModelAdapter.NameViewHolder> {


        @NonNull
        @Override
        public NameModelAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_row, parent, false);

            return new NameModelAdapter.NameViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull NameModelAdapter.NameViewHolder holder, int position) {

            NameModel nameModel = mNameList.get(position);
            holder.mTxtUrl.setText(nameModel.mUrl);
            holder.mTxtDate.setText((new Date(nameModel.mDate).toString()));


        }

        @Override
        public int getItemCount() {
            return mNameList.size();
        }

        class NameViewHolder extends RecyclerView.ViewHolder {

            TextView mTxtUrl;
            TextView mTxtDate;

            NameViewHolder(View itemView) {
                super(itemView);
                mTxtUrl = itemView.findViewById(R.id.tvUrl);
                mTxtDate = itemView.findViewById(R.id.tvDate);
                ImageButton btnDelete = itemView.findViewById(R.id.btnDelete);
                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = getAdapterPosition();
                        NameModel nameModel = mNameList.get(pos);
                        namesViewModel.removeName(nameModel.mId);
                    }
                });
            }
        }
    }
}
