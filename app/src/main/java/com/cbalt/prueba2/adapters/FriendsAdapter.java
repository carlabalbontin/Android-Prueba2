package com.cbalt.prueba2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cbalt.prueba2.FriendClickListener;
import com.cbalt.prueba2.R;
import com.cbalt.prueba2.data.Queries;
import com.cbalt.prueba2.models.Friend;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private List<Friend> friends = new Queries().friends();
    private FriendClickListener listener;

    public FriendsAdapter(FriendClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_friend, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.friendNameTv.setText(friend.getName());
        holder.friendDescriptionTv.setText(friend.getDescription());

        int thumbImg = imageResource(friend.getType());
        holder.friendImage.setImageResource(thumbImg);

        holder.friendItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int auxPosition = holder.getAdapterPosition();
                Friend auxFriend = friends.get(auxPosition);
                if (listener != null) {
                    listener.clickedID(auxFriend.getId());
                } else {
                    Log.d("LISTENER_NULL", "LISTENER IS NULL");
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public int imageResource(String friendType){
        int imageResource;

        switch (friendType) {
            case "dog":
                return R.mipmap.thumb_dog;
            case "cat":
                return R.mipmap.thumb_cat;
            case "human":
                return R.mipmap.thumb_human;
        }
        return R.mipmap.thumb_dog;
    }

    public void addFriend(Friend friend){
        friends.add(friend);
        notifyDataSetChanged();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout friendItemLayout;
        private TextView friendNameTv;
        private TextView friendDescriptionTv;
        private ImageView friendImage;

        public ViewHolder(View itemView) {
            super(itemView);

            friendItemLayout = itemView.findViewById(R.id.friendItem);
            friendNameTv = itemView.findViewById(R.id.friendNameTv);
            friendDescriptionTv = itemView.findViewById(R.id.friendDescriptionTv);
            friendImage = itemView.findViewById(R.id.friendImage);
        }
    }
}
