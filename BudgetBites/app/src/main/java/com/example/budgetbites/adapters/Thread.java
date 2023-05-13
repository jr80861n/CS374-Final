package com.example.budgetbites.adapters;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetbites.MainActivity;

public class Thread extends java.lang.Thread {
    public MainActivity activity;
    public Thread(MainActivity ac) {
        activity = ac;
    }

    public void run() {
        while(true) {

            try {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.getQuote();
                    }
                });
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
