package com.flege.buku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class EditActivity extends AppCompatActivity {
    EditText edtId, edtTitle, edtAuthor_name;
    Button btUpdate, btDelete, btBack;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        edtId = findViewById(R.id.edtId);
        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor_name = findViewById(R.id.edtAuthor_name);
        Intent mIntent = getIntent();
        edtId.setText(mIntent.getStringExtra("Id"));
        edtId.setTag(edtId.getKeyListener());
        edtId.setKeyListener(null);
        edtTitle.setText(mIntent.getStringExtra("Title"));
        edtAuthor_name.setText(mIntent.getStringExtra("Author Name"));
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        btUpdate = findViewById(R.id.btUpdate2);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPutDelBuku> updateKontakCall = mApiInterface.putBuku(
                        edtId.getText().toString(),
                        edtTitle.getText().toString(),
                        edtAuthor_name.getText().toString());
                updateKontakCall.enqueue(new Callback<PostPutDelBuku>() {
                    @Override
                    public void onResponse(Call<PostPutDelBuku> call, Response<PostPutDelBuku> response) {
//                        Log.d("cek",String.valueOf(response.body().getMessage()));
//                        Log.d("cek raw",String.valueOf(response.raw()));
                        MainActivity.ma.refresh();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPutDelBuku> call, Throwable t) {
                        Log.d("test",t.getMessage());
                        Toast.makeText(getApplicationContext(), "Error update", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btDelete = findViewById(R.id.btDelete2);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().trim().isEmpty()==false){
                    Call<PostPutDelBuku> deleteKontak = mApiInterface.deleteBuku(edtId.getText().toString());
                    deleteKontak.enqueue(new Callback<PostPutDelBuku>() {
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
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
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
