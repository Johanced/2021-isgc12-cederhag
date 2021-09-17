package com.example.labb2;

import android.view.View;
import android.widget.TextView;


public class graphics {

    public graphics(){

    }

    public void setVisibility(TextView v, String choice){
        if(choice == "visible") {
            v.setVisibility(View.VISIBLE);

        }else if(choice == "invisible") {
            v.setVisibility(View.INVISIBLE);
        }
    }


}
