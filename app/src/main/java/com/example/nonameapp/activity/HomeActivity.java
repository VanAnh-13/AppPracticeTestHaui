package com.example.nonameapp.activity;

import android.app.Activity;
import android.widget.LinearLayout;

import org.jetbrains.annotations.NotNull;

public class HomeActivity extends Activity {
    @NotNull
    public final LinearLayout linerLayout;

    public HomeActivity(@NotNull LinearLayout linerLayout) {
        this.linerLayout = linerLayout;
    }
}
