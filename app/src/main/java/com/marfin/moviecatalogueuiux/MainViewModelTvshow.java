package com.marfin.moviecatalogueuiux;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MainViewModelTvshow extends ViewModel {
    private static final String API_KEY = "e9f610d23471a7218913b192cff86b0b";
    private MutableLiveData<ArrayList<Tvshow>> listTvshow = new MutableLiveData<>();

    void setTvshow() {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Tvshow> listItems = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY + "&language=en-US&page=1";
        Log.d("url", url);
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject tvshow1 = list.getJSONObject(i);
                        Tvshow tvshow = new Tvshow(tvshow1);
                        listItems.add(tvshow);
                    }
                    listTvshow.postValue(listItems);
                } catch (Exception e) {
                    Log.d("onSuccess: Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    LiveData<ArrayList<Tvshow>> getTvshow() {
        return listTvshow;
    }
}
