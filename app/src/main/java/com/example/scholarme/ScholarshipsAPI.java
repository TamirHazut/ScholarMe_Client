package com.example.scholarme;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface ScholarshipsAPI {

    @GET("/scholarships")
    Call<List<Scholarship>> getScholarships(@Body Search_Scholarship search_scholarship);

}