package com.clj.router;

import android.content.Context;

import java.util.HashMap;


public abstract class Action {

    public abstract Object startAction(Context context, HashMap<String, Object> requestData);

}
