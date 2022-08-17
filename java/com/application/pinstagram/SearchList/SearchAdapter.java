package com.application.pinstagram.SearchList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.pinstagram.Model.UserData;
import com.application.pinstagram.R;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    private final List<UserData> userDtoList;
    private final Context context;
    private final SearchInterface searchInterface;

    public SearchAdapter(List<UserData> userDtoList, Context context, SearchInterface searchInterface) {
        this.userDtoList = userDtoList;
        this.context = context;
        this.searchInterface = searchInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData userDto = userDtoList.get(position);
        String userName=userDto.getUserName();
        holder.userName.setText(userName);
       // holder.rootView.setOnClickListener((view -> searchInterface.onUserClick(UserData,view, holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return userDtoList.size();
    }

    public interface SearchInterface{
        void onUserClick(UserData userDto,View view,int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final View rootView;

        public ViewHolder(View view) {
            super(view);
            rootView = view;
            userName = view.findViewById(R.id.username_search);
        }

    }
}
