package com.jtrev.lightactivator;

import android.widget.ToggleButton;

/**
 * Created by jtrev on 10/28/2015.
 */
public class SuperLightToggle {
    private ToggleButton lightToggle;
    private String roomName;

    public SuperLightToggle(ToggleButton lightToggle, String roomName) {
        this.lightToggle = lightToggle;
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ToggleButton getLightToggle() {
        return lightToggle;
    }

    public void setLightToggle(ToggleButton lightToggle) {
        this.lightToggle = lightToggle;
    }


}
