package com.application.pinstagram.SearchList;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.application.pinstagram.Model.UserData;
import com.application.pinstagram.MyProfile.MyProfileFragment;
import com.application.pinstagram.R;
import com.application.pinstagram.Retrofit.MainBuilder;
import com.application.pinstagram.Retrofit.IApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchUserList extends AppCompatActivity implements SearchAdapter.SearchInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String query= getIntent().getStringExtra("query");
        Retrofit retrofit= MainBuilder.getInstance();
//        Call<List<UserData>> users=retrofit.create(IApi.class).search(query);
//        users.enqueue(new Callback<List<UserData>>() {
//            @Override
//            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
//                RecyclerView recyclerView = findViewById(R.id.recycle_search);
//                SearchAdapter searchAdapter=new SearchAdapter(response.body(),getApplicationContext(),SearchUserList.this);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                recyclerView.setAdapter(searchAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<UserData>> call, Throwable t) {
//
//            }
//        });


    }


    @Override
    public void onUserClick(UserData userDto, View view, int position) {
        Intent i = new Intent(this, MyProfileFragment.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("target",userDto);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}