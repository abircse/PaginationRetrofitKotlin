package com.example.recyclerviewpagination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.recyclerviewpagination.adapter.PaginationAdapter;
import com.example.recyclerviewpagination.model.Data;
import com.example.recyclerviewpagination.model.Response;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<Data> list;
    private PaginationAdapter adapter;
    private ApiInterface apiInterface;
    private LinearLayoutManager layoutManager;

    private int page_number = 1;
    private int item_count = 8;
    private boolean isLoading = true;
    private int pastVisibleItems, VisibleItemCount, TotalItemCount, previousTotal = 0;
    private int view_thereshold = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // Network Call
        list = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        Call<Response> call = apiInterface.fetchData(page_number,item_count);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                progressBar.setVisibility(View.GONE);
                list= response.body().getData();
                adapter = new PaginationAdapter(list,MainActivity.this);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        // rEYCLERVIEW Scroll listerner
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                VisibleItemCount = layoutManager.getChildCount();
                TotalItemCount = layoutManager.getItemCount();
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                if (dy>0)
                {
                    if (isLoading)
                    {
                        if (TotalItemCount > previousTotal)
                        {
                            isLoading = false;
                            previousTotal = TotalItemCount;
                        }
                    }

                    if (!isLoading && (TotalItemCount - VisibleItemCount) <= (pastVisibleItems+view_thereshold))
                    {
                        page_number++;
                        PerformPagination();
                        isLoading = true;
                    }

                }
            }
        });


    }

    private void PerformPagination()
    {
        progressBar.setVisibility(View.VISIBLE);
        Call<Response> call = apiInterface.fetchData(page_number,item_count);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                if (response.body().getStatus().equals(200))
                {
                    progressBar.setVisibility(View.GONE);
                    list= response.body().getData();
                    adapter.addpeople(list);
                    Toast.makeText(MainActivity.this, "Page "+page_number+" is loaded", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "No More Data", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}