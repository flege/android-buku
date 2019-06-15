package com.flege.buku.Rest;

import com.flege.buku.Model.GetBuku;
import com.flege.buku.Model.PostPutDelBuku;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @GET("read.php")
    Call<GetBuku> getBuku();
    @FormUrlEncoded
    @POST("create.php")
    Call<PostPutDelBuku> postBuku(@Field("title") String title, @Field("author_name") String author_name);
    @FormUrlEncoded
    @PUT("update.php")
    Call<PostPutDelBuku> putBuku(@Field("id") String id, @Field("title") String nama, @Field("author_name") String nomor);
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "delete.php", hasBody = true)
    Call<PostPutDelBuku> deleteBuku(@Field("id") String id);
}
