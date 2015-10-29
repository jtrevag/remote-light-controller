package com.jtrev.lightactivator;

import android.widget.Switch;

/**
 * Created by jtrev on 10/28/2015.
 */
public class SuperLightSwitch {
    private Switch lightSwitch;

    private String roomName;

    public SuperLightSwitch(Switch lightSwitch, String roomName) {
        this.lightSwitch = lightSwitch;
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Switch getLightSwitch() {
        return lightSwitch;
    }

    public void setLightSwitch(Switch lightSwitch) {
        this.lightSwitch = lightSwitch;
    }


}
