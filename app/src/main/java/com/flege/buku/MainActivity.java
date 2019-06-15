package com.flege.buku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flege.buku.Adapter.BukuAdapter;
import com.flege.buku.Model.Buku;
import com.flege.buku.Model.GetBuku;
import com.flege.buku.Rest.ApiClient;
import com.flege.buku.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btIns;
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIns = findViewById(R.id.btIns);
        btIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        ma=this;
        refresh();
    }

    public void refresh() {
        Call<GetBuku> bukuCall = mApiInterface.getBuku();
        bukuCall .enqueue(new Callback<GetBuku>() {
            @Override
            public void onResponse(Call<GetBuku> call, Response<GetBuku> response) {
                List<Buku> BukuList = response.body().getListDataBuku();
                Log.d("Retrofit Get", "Jumlah data Kontak: " + BukuList.size());
                mAdapter = new BukuAdapter(BukuList );
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetBuku> call, Throwable t) {
                Log.e("Retrofit Gets", t.toString());
            }
        });
    }
}
