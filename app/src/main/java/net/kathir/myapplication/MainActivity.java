package net.kathir.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.kathir.myapplication.LiveDataWithLocalDB.LocalDBLiveDataViewActivity;
import net.kathir.myapplication.retrofitLiveData.ui.LiveDataRetrofitActivity;
import net.kathir.myapplication.simpleLiveData.SimpleLiveDataViewActivity;

public class MainActivity extends AppCompatActivity {

    Button livedataRetrofitView,simpleLiveDataView,localDbDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        livedataRetrofitView = (Button)findViewById(R.id.livedata_exampleView);
        simpleLiveDataView = (Button)findViewById(R.id.simple_livedata_view);
        localDbDataView = (Button)findViewById(R.id.localDb_livedata_view);

        livedataRetrofitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LiveDataRetrofitActivity.class);
                startActivity(intent);

            }
        });

        simpleLiveDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, SimpleLiveDataViewActivity.class);
                startActivity(intent);

            }
        });

        localDbDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LocalDBLiveDataViewActivity.class);
                startActivity(intent);


            }
        });
    }
}
