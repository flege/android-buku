package com.flege.buku.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBuku {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Buku> listDataBuku;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Buku> getListDataBuku() {
        return listDataBuku;
    }
    public void setListDataBuku(List<Buku> listDataBuku) {
        this.listDataBuku= listDataBuku;
    }
}
