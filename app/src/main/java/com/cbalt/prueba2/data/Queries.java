package com.cbalt.prueba2.data;

import com.cbalt.prueba2.models.Friend;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    public List<Friend> friends(){
        List<Friend> friends = new ArrayList<>();
        List<Friend> friendList = Friend.listAll(Friend.class);

        if(friendList != null && friendList.size() > 0 ){
            friends.addAll(friendList);
        }

        return friends;

    }
}
