package com.jtrev.lightactivator;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LightActivity extends AppCompatActivity {

    private HashMap<String, Switch> switchMap = new HashMap<String, Switch>();
    private String lightServiceUrl = "http://173.74.40.164:3000/lightService";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestQueue = ((LightApplication) getApplication()).getRequestQueue();
        initializeSwitches();


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

    private void initializeSwitches(){
        final SuperLightSwitch computerSwitch = new SuperLightSwitch( (Switch) findViewById(R.id.computerRoomSwitch), "computer_room");

        computerSwitch.getLightSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSwitchAndSendRequest(isChecked, computerSwitch.getRoomName(), requestQueue);
            }
        });

        final SuperLightSwitch kitchenSwitch = new SuperLightSwitch( (Switch) findViewById(R.id.kitchenSwitch), "living_room_back");

        kitchenSwitch.getLightSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSwitchAndSendRequest(isChecked, kitchenSwitch.getRoomName(), requestQueue);
            }
        });

        final SuperLightSwitch livingRoomSwitch = new SuperLightSwitch( (Switch) findViewById(R.id.livingRoomSwitch), "living_room_front");

        livingRoomSwitch.getLightSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSwitchAndSendRequest(isChecked, livingRoomSwitch.getRoomName(), requestQueue);
            }
        });

        final SuperLightSwitch bedroomOneSwitch = new SuperLightSwitch( (Switch) findViewById(R.id.bedroomSwitch1), "bedroom_one");

        bedroomOneSwitch.getLightSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSwitchAndSendRequest(isChecked, bedroomOneSwitch.getRoomName(), requestQueue);
            }
        });

        final SuperLightSwitch bedroomTwoSwitch = new SuperLightSwitch( (Switch) findViewById(R.id.bedroomSwitch2), "bedroom_two");

        bedroomTwoSwitch.getLightSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSwitchAndSendRequest(isChecked, bedroomTwoSwitch.getRoomName(), requestQueue);
            }
        });


    }

}
