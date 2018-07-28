package com.cbalt.prueba2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbalt.prueba2.models.Friend;

public class FriendDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_details);
        Intent intent = getIntent();

        final long friendID = intent.getLongExtra(ScrollingActivity.FRIEND_ID, 0);

        TextView friendName = findViewById(R.id.friendDetailsName);
        TextView friendPhoneNumber = findViewById(R.id.friendDetailsPhoneNumber);
        TextView friendAddress = findViewById(R.id.friendDetailsAddress);
        TextView friendDescription = findViewById(R.id.friendDetailsDescription);
        ImageView friendImage = findViewById(R.id.friendDetailsImage);

        final Friend friend = Friend.findById(Friend.class, friendID);
        friendName.setText(friend.getName());
        friendDescription.setText(friend.getDescription());
        friendPhoneNumber.setText(String.valueOf(friend.getPhoneNumber()));
        friendAddress.setText(friend.getAddress());
        int friendImageResource = imageResource(friend.getType());
        friendImage.setImageResource(friendImageResource);

        Button deleteFriendButton = findViewById(R.id.deleteFriendBtn);
        deleteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dialogMessage = "¿Estás seguro que ya no quieres ser amigo de " + friend.getName() + "?";
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Friend formerFriend = Friend.findById(Friend.class, friendID);
                                formerFriend.delete();
                                Intent intent = new Intent(FriendDetailsActivity.this, ScrollingActivity.class);
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendDetailsActivity.this);
                builder.setMessage(dialogMessage).setPositiveButton("Sí, seguro", dialogClickListener)
                        .setNegativeButton("Lo reconsideraré", dialogClickListener).show();
            }
        });
    }


    public int imageResource(String friendType){
        int imageResource;

        switch (friendType) {
            case "dog":
                return R.mipmap.full_dog;
            case "cat":
                return R.mipmap.full_cat;
            case "human":
                return R.mipmap.full_human;
        }
        return R.mipmap.full_dog;
    }
}
