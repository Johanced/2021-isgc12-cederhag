package com.example.labb4.MainView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.labb4.ParseTask.JsonParser;
import com.example.labb4.ParseTask.MyAsyncTask;
import com.example.labb4.ParseTask.TaskCallback;
import com.example.labb4.PersistentData.localDBPreferences;
import com.example.labb4.R;
import com.example.labb4.VolleyClasses.VolleyCallback;
import com.example.labb4.VolleyClasses.VolleyMain;

import java.util.ArrayList;

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

        volleyManager = new VolleyMain(getApplicationContext());
        jsonParser = new JsonParser();

        movieList = new ArrayList<>();
        savedMovieList = new ArrayList<>();

        movAdapt = new MovieViewAdapter(this, movieList);
        savedMovAdapt = new MovieViewAdapter(this, savedMovieList);

        saveListView.setAdapter(savedMovAdapt);
        lv1.setAdapter(movAdapt);

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
    public void updatedData(ArrayList<Movie> itemsArrayList, ArrayAdapter<Movie> adapter) {

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
        Boolean testString = volleyManager.getSearchString(String.valueOf(searchTextField.getText()));
        Log.d(TAG, "searchBtnClicked: getSearchString: "+testString);
        if(testString.equals(true)){
            volleyManager.buildUrl("0","10");
            volleyManager.sendRequest(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    completeResponse = result;
                    Log.d(TAG, "onSuccess: got response back to main!");

                    // AsyncTask to parse response
                    // AsyncTask.execute
                    myParseTask = new MyAsyncTask(jsonParser,"Search", completeResponse, new TaskCallback() {
                        @Override
                        public void onResponseGet(ArrayList<Movie> list) {
                            updatedData(list, movAdapt);
                        }
                    });
                    myParseTask.execute();
                }

                @Override
                public void onError(String result) {
                    Log.d(TAG, "onError: "+result);
                    showToast("Error: I Blame MyApiFilms for this..");

                }
            });
        }
        else{
            showToast("Please enter a name..");
        }

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
            }

        }catch(IndexOutOfBoundsException e){
            Log.d(TAG, "saveBtnClicked: error: "+e);
        }

    }

    public void searchSimilarBtnClicked(View view) {

        try{
            Movie mov = movieList.get(movieListPos);
            showToast("Processing Similar Search..");
            movieList.clear();

            Boolean testString = volleyManager.getSearchString(mov.getName());
            if(testString.equals(true)){
                volleyManager.buildUrl("1","1");
                volleyManager.sendRequest(new VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        completeResponse = result;
                        Log.d(TAG, "onSuccess: got response back to main!");

                        // AsyncTask to parse response
                        // AsyncTask.execute
                        myParseTask = new MyAsyncTask(jsonParser,"SearchSimilar", completeResponse, new TaskCallback() {
                            @Override
                            public void onResponseGet(ArrayList<Movie> list) {
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
            }else{
                showToast("Please enter a name..");
            }


        }catch(IndexOutOfBoundsException e){
            Log.d(TAG, "searchSimilarBtnClicked: Error, "+e);
            e.printStackTrace();
            showToast("Something went wrong, try again..");
        }

    }
    public void removeMovieBtn(View view) {
       try{
           if(savedMovieList.isEmpty()){
               showToast("Can't remove, list is empty!");
           }else{
               savedMovieList.remove(savedMovieListPos);
               savedMovAdapt.notifyDataSetChanged();
           }
       }catch(NullPointerException | IndexOutOfBoundsException e){
           Log.d(TAG, "removeMovieBtn: Error: "+e);
           showToast("Something went wrong, try again!");
       }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: savedMovieList.count: "+savedMovieList.size());
        localDBPreferences localDB = new localDBPreferences(getApplicationContext());
        localDB.testSaveData(savedMovieList);
        Log.d(TAG,"Saved: "+savedMovieList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"I am in onStart");
        localDBPreferences localDb = new localDBPreferences(getApplicationContext());
        ArrayList<Movie> holderList = localDb.testLoadData();
        Log.d(TAG, "onStart: holderList: "+holderList);
        savedMovieList.clear();
        for (Movie mov: holderList) {
            savedMovieList.add(mov);
            Log.d(TAG, "loaded movie : "+mov.name);
        }
        savedMovAdapt.notifyDataSetChanged();
    }

}