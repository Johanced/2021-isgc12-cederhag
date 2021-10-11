package com.example.Labb3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RequestManager reqMan;
    private String completeResponse;
    private ListView artistListView;
    private ArrayAdapter<Artist> adapt;
    private EditText searchTextField;
    private EditText limitSearchField;
    private XmlDebunker parser;
    private List<Artist> artistList;
    private MyAsyncTask myParseTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        artistListView = findViewById(R.id.ListView);
        searchTextField = findViewById(R.id.searchField);
        limitSearchField = findViewById(R.id.limitSearchField);

        reqMan = new RequestManager(this);

        artistList = new ArrayList<>();

        parser = new XmlDebunker(new Toast(this));

        adapt = new ArrayAdapter<>(this, R.layout.item_view, R.id.itemTextView, artistList);
        artistListView.setAdapter(adapt);


    }
    public void updatedData(List itemsArrayList) {

        adapt.clear();
        if (itemsArrayList != null){
            for (Object a : itemsArrayList) {
                adapt.insert((Artist) a, adapt.getCount());
            }
        }
        adapt.notifyDataSetChanged();

    }

    public void triggerTask(View view) {
        // Clear ArtistList for each search!
        artistList.clear();
        Log.d(TAG, "Search text: "+searchTextField.getText());

        reqMan.getSearchName(String.valueOf(searchTextField.getText()));
        reqMan.getLimitNumber(limitSearchField.getText().toString());

        reqMan.createVolleyReq(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
               
                completeResponse = result;
                Log.d(TAG, "completeResponse: "+completeResponse);

                // New AsyncTask
                myParseTask = new MyAsyncTask(parser,completeResponse , result1 -> {
                    artistList = result1;
                    // Update ListView Data last!
                    updatedData(artistList);
                });
                myParseTask.execute();
            }

            @Override
            public void onError(String result) {
                Log.d(TAG, "onError: Error Occurred with volley request");
            }
        });



    }
}