package net.kathir.myapplication.simpleLiveData;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import net.kathir.myapplication.R;

public class SimpleLiveDataViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_view);

        ListView listView = (ListView)findViewById(R.id.list);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);

        progressBar.setVisibility(View.VISIBLE);

        SimpleLiveDataModel model = ViewModelProviders.of(this).get(SimpleLiveDataModel.class);

        model.getNameList().observe(this,nameList -> {


            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);


        });


    }
}
