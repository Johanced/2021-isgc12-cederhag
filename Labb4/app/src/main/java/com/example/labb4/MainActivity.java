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
    private MovieViewAdapter movAdapt;
    private MyAsyncTask myParseTask;
    private JsonParser jsonParser;
    private int movieListPos;
    private int savedMovieListPos;
    private MovieViewAdapter savedMovAdapt;
    //private localDBPreferences localDB;
    private ListView saveListView;
    private ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: i am in onCreate");

        searchTextField = findViewById(R.id.searchTextField);
        lv1 = findViewById(R.id.ListView);
        saveListView = findViewById(R.id.ListView3);

        //TODO; Add possibility of saving data, "savedMovieList" should be Loaded on startup and saved on shutdown! -> SharedPreferences
        //localDBPreferences.initializeInstance(getApplicationContext());
        //localDB = localDBPreferences.getInstance();

        volleyManager = new VolleyMain(getApplicationContext());
        jsonParser = new JsonParser();

        movieList = new ArrayList<Movie>();
        savedMovieList = new ArrayList<Movie>();

        movAdapt = new MovieViewAdapter(this, movieList);
        savedMovAdapt = new MovieViewAdapter(this, savedMovieList);

        saveListView.setAdapter(savedMovAdapt);
        lv1.setAdapter(movAdapt);

        //TODO; Test Load Data From Here

        saveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                savedMovieListPos = pos;
                Log.d(TAG, "onItemClick: savePos = "+savedMovieListPos);
            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                movieListPos = pos;
                Log.d(TAG, "onItemClick: Pos = "+movieListPos);
            }
        });

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
        volleyManager.buildUrl("0",10);
        volleyManager.sendRequest(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                completeResponse = result;
                Log.d(TAG, "onSuccess: got response back to main!");

                // AsyncTask to parse response
                // AsyncTask.execute
                myParseTask = new MyAsyncTask(jsonParser,"Search", completeResponse, new TaskCallback() {
                    @Override
                    public void onResponseGet(List<Movie> list) {
                        updatedData(list, movAdapt);
                    }
                });
                myParseTask.execute();
            }

            @Override
            public void onError(String result) {
                Log.d(TAG, "onError: "+result);
                showToast("Error: MyApiFilms is shaky..");

            }
        });
    }

    public void saveBtnClicked(View view) {
        try{
            if(movieList.isEmpty()){
                showToast("Can't save movie, please select a movie!");
            }
            else{
                Movie aMovie = (Movie) lv1.getItemAtPosition(movieListPos);
                Log.d(TAG, "saveBtnClicked: movieName = "+aMovie.getName());

                savedMovieList.add(aMovie);
                Log.d(TAG, "saveBtnClicked: savedMovieList.count: "+savedMovieList.size());
                savedMovAdapt.notifyDataSetChanged();
                //updatedData(savedMovieList, savedMovAdapt);
            }

        }catch(IndexOutOfBoundsException e){
            Log.d(TAG, "saveBtnClicked: error: "+e);
        }

    }

    public void searchSimilarBtnClicked(View view) {

        try{
            //TODO; TEST
            Movie mov = movieList.get(movieListPos);
            showToast("Processing Similar Search..");
            movieList.clear();

            volleyManager.getSearchString(mov.getName());
            volleyManager.buildUrl("1",1);
            volleyManager.sendRequest(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    completeResponse = result;
                    Log.d(TAG, "onSuccess: got response back to main!");

                    // AsyncTask to parse response
                    // AsyncTask.execute
                    myParseTask = new MyAsyncTask(jsonParser,"SearchSimilar", completeResponse, new TaskCallback() {
                        @Override
                        public void onResponseGet(List<Movie> list) {
                            updatedData(list, movAdapt);
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
            e.printStackTrace();
            showToast("Something went wrong, try again..");
        }

    }
    public void removeMovieBtn(View view) {
        if(savedMovieList.isEmpty()){
            showToast("Can't remove, list is empty!");
        }else{
            savedMovieList.remove(savedMovieListPos);
            savedMovAdapt.notifyDataSetChanged();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d(TAG, "onPause: savedMovieList.count: "+savedMovieList.size());
        localDBPreferences localDB = new localDBPreferences(getApplicationContext());
        //localDB.saveCurrentData(savedMovieList);
        localDB.testSaveData(savedMovieList);
        Log.d(TAG,"Saved: "+savedMovieList);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d(TAG,"I am in onstart");
        localDBPreferences localDb = new localDBPreferences(getApplicationContext());
        ArrayList<Movie> holderList = localDb.testLoadData();
        Log.d(TAG, "onStart: Loading movies...");
        Log.d(TAG, "onStart: holderList: "+holderList);
        savedMovieList.clear();
        for (Movie mov: holderList) {
            savedMovieList.add(mov);
            Log.d(TAG, "loaded movie : "+mov.name);
        }
        savedMovAdapt.notifyDataSetChanged();
    }

}