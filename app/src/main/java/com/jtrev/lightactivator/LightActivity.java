package com.jtrev.lightactivator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LightActivity extends AppCompatActivity {

    private HashMap<String, ToggleButton> switchMap = new HashMap<String, ToggleButton>();
    private ArrayList<String> lightRooms = new ArrayList<String>();
    private String lightServiceUrl = "http://173.74.40.164:3000/lightService";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        populateLightArrayList();
        requestQueue = ((LightApplication) getApplication()).getRequestQueue();
        initializeSwitches();
        TextView kitchenText = (TextView) findViewById(R.id.kitchenTextView);


    }

    private void populateLightArrayList() {
        switchMap.put("living_room_front", (ToggleButton) findViewById(R.id.livingRoomtoggleButton));
        switchMap.put("living_room_back", (ToggleButton) findViewById(R.id.kitchenToggleButton));
        switchMap.put("computer_room", (ToggleButton) findViewById(R.id.computerRoomToggleButton));
        switchMap.put("bedroom_one", (ToggleButton) findViewById(R.id.bedroomRightToggleButton));
        switchMap.put("bedroom_two", (ToggleButton) findViewById(R.id.bedroomLeftToggleButton));
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_light, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkSwitchAndSendRequest ( boolean isChecked, String roomName, RequestQueue
    requestQueue){
        LightSwitchRequest lsr = null;

        if (isChecked) {
            lsr = new LightSwitchRequest(Request.Method.PUT, lightServiceUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //mPostCommentResponse.requestCompleted();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mPostCommentResponse.requestEndedWithError(error);
                }
            }, roomName, "on");

        } else {
            lsr = new LightSwitchRequest(Request.Method.PUT, lightServiceUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //mPostCommentResponse.requestCompleted();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mPostCommentResponse.requestEndedWithError(error);
                }
            }, roomName, "off");
        }


        requestQueue.add(lsr);
    }

    /**
     * Iterate through the map of ToggleButtons and set the OnCheckChangeListener for all of them.
     * The key is the actual name of the room in mongoDB, and the value is the ToggleButton.
     * We send the key to the method checkSwitchAndSendRequest so we can
     *
     */
    private void initializeSwitches(){
        Iterator<Map.Entry<String, ToggleButton>> iterator = switchMap.entrySet().iterator();
        while(iterator.hasNext()){
            final Map.Entry<String,ToggleButton> light = iterator.next();
            light.getValue().setChecked(getLightStatus(light.getKey()));
            light.getValue().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkSwitchAndSendRequest(isChecked, light.getKey(), requestQueue);
                }
            });

        }

    }

    private boolean getLightStatus(final String roomName) {
        StringRequest sr = null;

        String getUrl = lightServiceUrl + "?room=" + roomName;
        sr = new StringRequest(Request.Method.GET, getUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("lightActivity", "Roomname: " + roomName +" Got response: " + response);

                }
                  }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.w("lightActivity", "Got an error:" + error);
                //mPostCommentResponse.requestEndedWithError(error);
        }
        });

        requestQueue.add(sr);



        return false;
    }

}
