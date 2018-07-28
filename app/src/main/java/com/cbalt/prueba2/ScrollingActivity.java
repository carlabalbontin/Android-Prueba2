package com.cbalt.prueba2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cbalt.prueba2.adapters.FriendsAdapter;
import com.cbalt.prueba2.models.Friend;

public class ScrollingActivity extends AppCompatActivity implements FriendClickListener {

    private FriendsAdapter adapter;
    public static final String FRIEND_ID = "com.cbalt.prueba2.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.friendsRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*Friend friend1 = new Friend("Canela", "Vive en mi edificio y le gusta perseguir una pelota chica.", "dog",  "Mi Edificio", 111111);
        friend1.save();
        Friend friend3 = new Friend("Jesu", "Me quedé en su casa un par de veces y me trató muy bien", "human", "El Golf", 212122);
        friend3.save();
        Friend friend2 = new Friend("Kobu", "Shitzu muy peludo y juguetón.", "dog", "Alemania", 342432);
        friend2.save();
        Friend friend4 = new Friend("Pepa", "Me trata bien pero me dice Trot-ass y no sé si se está burlando de mí", "human", "Lejos", 521521);
        friend4.save();
        Friend friend5 = new Friend("Petite", "Me cae muy bien aunque no le gusta jugar conmigo", "cat", "Vitacura", 432432);
        friend5.save();*/

        adapter = new FriendsAdapter(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abrir Diálogo
                final Dialog dialog = new Dialog(ScrollingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_new_friend);

                // Set up el Spinner
                final Spinner spinner = dialog.findViewById(R.id.newFriendType);
                ArrayAdapter<CharSequence> spinnerOptions = ArrayAdapter.createFromResource(ScrollingActivity.this,
                        R.array.friends_type, android.R.layout.simple_spinner_item);
                spinnerOptions.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerOptions);

                // Acción del Diálogo
                Button saveBtn = dialog.findViewById(R.id.saveNewFriend);
                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText newFriendNameEt = dialog.findViewById(R.id.newFriendName);
                        EditText newFriendDescriptionEt = dialog.findViewById(R.id.newFriendDescription);
                        EditText newFriendPhoneNumberEt = dialog.findViewById(R.id.newFriendPhoneNumber);
                        EditText newFriendAddressEt = dialog.findViewById(R.id.newFriendAddress);

                        String newFriendName = newFriendNameEt.getText().toString();
                        String newFriendDescription = newFriendDescriptionEt.getText().toString();
                        String newFriendPhoneNumber = newFriendPhoneNumberEt.getText().toString();
                        String newFriendAddress = newFriendAddressEt.getText().toString();
                        String newFriendType = spinner.getSelectedItem().toString().toLowerCase();

                        if(newFriendName.trim().length() > 0 && newFriendDescription.trim().length() > 0 && newFriendPhoneNumber.trim().length() > 0 && newFriendAddress.trim().length() > 0){
                            int newFriendPhone = Integer.parseInt(newFriendPhoneNumber);
                            Friend newFriend = new Friend(newFriendName, newFriendDescription, newFriendType, newFriendAddress, newFriendPhone);
                            newFriend.save();
                            updateList(newFriend);
                        }

                        dialog.dismiss();

                    }
                });
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    public void updateList(Friend newFriend) {
        adapter.addFriend(newFriend);
    }

    @Override
    public void clickedID(long id) {
        Intent intent = new Intent(ScrollingActivity.this, FriendDetailsActivity.class);
        intent.putExtra(FRIEND_ID, id);
        startActivity(intent);
    }

}
