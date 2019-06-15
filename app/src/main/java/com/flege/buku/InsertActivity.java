package com.flege.buku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flege.buku.Model.PostPutDelBuku;
import com.flege.buku.Rest.ApiClient;
import com.flege.buku.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    EditText edtTitle, edtAuthor_name;
    Button btInsert, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor_name = findViewById(R.id.edtAuthor_name);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btInsert = findViewById(R.id.btInserting);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelBuku> postKontakCall = mApiInterface.postBuku(edtTitle.getText().toString(), edtAuthor_name.getText().toString());
                postKontakCall.enqueue(new Callback<PostPutDelBuku>() {
                    @Override
                    public void onResponse(Call<PostPutDelBuku> call, Response<PostPutDelBuku> response) {
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelBuku> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btBack = findViewById(R.id.btBackGo);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ma.refresh();
                finish();
            }
        });
    }
}
