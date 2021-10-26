package com.example.labb4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private VolleyMain volleyManager;
    private EditText searchTextField;
    private String completeResponse;
    private ArrayList<Movie> movieList;
    private ArrayList<Movie> savedMovieList;
    private ArrayAdapter<Movie> adapt;
    private ListView listview;
    private MyAsyncTask myParseTask;
    private JsonParser jsonParser;
    private ListView saveListView;
    private ArrayAdapter<Movie> saveAdapt;
    private int movieListPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchTextField = findViewById(R.id.searchTextField);
        listview = findViewById(R.id.ListView);
        saveListView = findViewById(R.id.ListView2);

        volleyManager = new VolleyMain(getApplicationContext());
        jsonParser = new JsonParser();
        movieList = new ArrayList<>();
        savedMovieList = new ArrayList<>();

        adapt = new ArrayAdapter<>(this, R.layout.item_view, R.id.itemTextView, movieList);
        saveAdapt = new ArrayAdapter<>(this, R.layout.item_view, R.id.itemTextView, savedMovieList);
        saveListView.setAdapter(saveAdapt);
        listview.setAdapter(adapt);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                movieListPos = pos;
                Log.d(TAG, "onItemClick: Pos = "+movieListPos);
            }
        });

        //TODO; Add possibility of saving data, "savedMovieList" should be Loaded on startup and saved on shutdown! -> SharedPreferences

    }
    public void updatedData(List itemsArrayList, ArrayAdapter<Movie> adapter) {

        adapter.clear();
        if (itemsArrayList != null){
            for (Object a : itemsArrayList) {
                adapter.insert((Movie) a, adapter.getCount());
            }
        }
        adapter.notifyDataSetChanged();

    }
    public void showToast(String txtMsg){
        Toast.makeText(this, txtMsg, Toast.LENGTH_SHORT).show();
    }

    public void searchBtnClicked(View view) {
        showToast("Processing Search..");
        movieList.clear();
        volleyManager.getSearchString(String.valueOf(searchTextField.getText()));
        volleyManager.buildUrl("0");
        volleyManager.sendRequest(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                completeResponse = result;
                Log.d(TAG, "onSuccess: "+completeResponse);

                // AsyncTask to parse response
                // AsyncTask.execute
                myParseTask = new MyAsyncTask(jsonParser,"Search", completeResponse, new TaskCallback() {
                    @Override
                    public void onResponseGet(List<Movie> list) {
                        updatedData(list, adapt);
                    }
                });
                myParseTask.execute();
            }

            @Override
            public void onError(String result) {
                Log.d(TAG, "onError: "+result);
            }
        });
    }

    public void saveBtnClicked(View view) {
        try{
            Movie aMovie = (Movie) listview.getItemAtPosition(movieListPos);
            Log.d(TAG, "saveBtnClicked: movieName = "+aMovie.getName());
            savedMovieList.add(aMovie);

            Log.d(TAG, "saveBtnClicked: "+savedMovieList.get(0).getName());
            saveAdapt.notifyDataSetChanged();
        }catch(NullPointerException e){
            Log.d(TAG, "saveBtnClicked: error: "+e);
        }

    }

    public void searchSimilarBtnClicked(View view) {

        try{
            //TODO; Add Toast "Processing Search..."
            Movie mov = (Movie)listview.getItemAtPosition(movieListPos);
            volleyManager.getSearchString(mov.getName());

            volleyManager.buildUrl("1");
            volleyManager.sendRequest(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    completeResponse = result;
                    Log.d(TAG, "onSuccess: "+completeResponse);

                    // AsyncTask to parse response
                    // AsyncTask.execute
                    myParseTask = new MyAsyncTask(jsonParser,"SearchSimilar", completeResponse, new TaskCallback() {
                        @Override
                        public void onResponseGet(List<Movie> list) {
                            movieList.clear();
                            updatedData(list, adapt);
                            searchTextField.setText(mov.getName());
                        }
                    });
                    myParseTask.execute();
                }

                @Override
                public void onError(String result) {
                    Log.d(TAG, "onError: "+result);
                }
            });

        }catch(IndexOutOfBoundsException e){
            Log.d(TAG, "searchSimilarBtnClicked: Error, "+e);
            //TODO; Add Error Toast!
        }

    }
}