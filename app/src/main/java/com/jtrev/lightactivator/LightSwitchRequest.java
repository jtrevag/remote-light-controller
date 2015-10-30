package com.jtrev.lightactivator;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jtrev on 10/28/2015.
 */
public class LightSwitchRequest extends StringRequest {
    String room;
    String state;

    public LightSwitchRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String room, String state) {
        super(method, url, listener, errorListener);
        this.room = room;
        this.state = state;
    }

    public LightSwitchRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener, String room, String state) {
        super(url, listener, errorListener);
        this.room = room;
        this.state = state;
    }

    @Override
    protected Map<String,String> getParams(){
        Map<String,String> params = new HashMap<String, String>();
        params.put("room",room);
        params.put("state",state);
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String,String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/x-www-form-urlencoded");
        return params;
    }



    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
