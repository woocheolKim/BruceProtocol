package com.example.bruceprotocol.Tools;

import android.util.Log;

public class Dlog {
    static final String TAG = "BruceProtocol";


    /** Log Level Error **/
    public static final <T> void e(T message) {
        if (BaseApplication.DEBUG) Log.e(TAG, buildLogMsg(String.valueOf(message)));
    }
    /** Log Level Warning **/
    public static final <T> void w(T message) {
        if (BaseApplication.DEBUG)Log.w(TAG, buildLogMsg(String.valueOf(message)));
    }
    /** Log Level Information **/
    public static final <T> void i(T message) {
        if (BaseApplication.DEBUG)Log.i(TAG, buildLogMsg(String.valueOf(message)));
    }
    /** Log Level Debug **/
    public static final <T> void d(T message) {
        if (BaseApplication.DEBUG)Log.d(TAG, buildLogMsg(String.valueOf(message)));
    }
    /** Log Level Verbose **/
    public static final <T> void v(T message) {
        if (BaseApplication.DEBUG)Log.v(TAG, buildLogMsg(String.valueOf(message)));
    }


    public static String buildLogMsg(String message) {

        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];

        StringBuilder sb = new StringBuilder();

        sb.append("[")
                .append(ste.getMethodName())
                .append("()")
                .append("]")
                .append(" :: ")
                .append(message)
                .append(" (")
                .append(ste.getFileName())
                .append(":")
                .append(ste.getLineNumber())
                .append(")");

        return sb.toString();

    }
}
