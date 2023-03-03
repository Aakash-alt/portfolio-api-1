package com.kushal.backend.constants;

import java.util.Calendar;
import java.util.Date;

public class Constant {
    public static final String BASE_URL = "";
    public static final String IMAGE_BASE_URL = "backend/images/";
    public static final String TEAM_SAVE_URL = IMAGE_BASE_URL + "team/";
    public static final String SPONSOR_SAVE_URL = IMAGE_BASE_URL + "sponsor/";

    public static final String EMAIL_ADDRESS = "kushalshah821@gmail.com";
    public static Long getTimeStamp() {
        return new Date().getTime();
    }
}
