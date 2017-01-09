package com.example.alexe1ka.iotalexe1ka;

/**
 * Created by alexe1ka on 04.12.2016.
 */

public final class ConstRequest {
    //потом адрес сервера вытащить и оставить в запросах только /*....
    public static final String WEMOS_ID = "/id";

    public static final String PIN0_ON = "/digital/0/1";
    public static final String PIN0_OFF = "/digital/0/0";

    public static final String PIN1_ON = "/digital/1/1";
    public static final String PIN1_OFF = "/digital/1/0";

    public static final String PIN2_ON = "/digital/2/1";
    public static final String PIN2_OFF = "/digital/2/0";

    public static final String PIN3_ON = "/digital/3/1";
    public static final String PIN3_OFF = "/digital/3/0";

    public static final String PIN4_ON = "/digital/4/1";
    public static final String PIN4_OFF = "/digital/4/0";

    public static final String PIN5_ON = "/digital/5/1";
    public static final String PIN5_OFF = "/digital/5/0";

    public static final String PIN6_ON = "/digital/6/1";
    public static final String PIN6_OFF = "/digital/6/0";

    public static final String PIN7_ON = "/digital/7/1";
    public static final String PIN7_OFF = "/digital/7/0";

    public static final String PIN8_ON = "/digital/8/1";
    public static final String PIN8_OFF = "/digital/8/0";

    public static final String RED_ON = "/digital/6/1";
    public static final String RED_OFF = "/digital/6/0";

    public static final String GREEN_ON = "/digital/4/1";
    public static final String GREEN_OFF = "/digital/4/0";

    public static final String BLUE_ON = "/digital/2/1";
    public static final String BLUE_OFF = "/digital/2/0";

    public static final String GET_TEMP = "/temperature";
    public static final String GET_HUM = "/humidity";

    public static String getUrl(String ip, String path) {
        return "http://" + ip + path;
    }
}
