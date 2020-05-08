package com.example.bruceprotocol.Tools;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class BaseApplication extends Application {
    public static boolean DEBUG = false;

    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;

    private static Context instance = null;

    public static Context getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerApplication());

        super.onCreate();
        if (instance == null){
            instance = getApplicationContext();
        }
        this.DEBUG = isDebuggable(this);
    }

    private boolean isDebuggable(Context context){
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }

        return debuggable;
    }

    private String getStackTrace(Throwable th){
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);

        Throwable cause = th;
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        final String stacktraceAsString = result.toString();
        printWriter.close();

        return stacktraceAsString;
    }

    class UncaughtExceptionHandlerApplication implements Thread.UncaughtExceptionHandler{

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            //예외처리를 하지 않고 DefaultUncaughtException으로 넘긴다.
            uncaughtExceptionHandler.uncaughtException(thread, ex);
        }

    }
}
