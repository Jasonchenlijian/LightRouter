package com.clj.router;


import android.util.Log;

import java.util.HashMap;

public abstract class Provider {

    private static final String TAG = Provider.class.getSimpleName();

    private HashMap<String, Action> mActions;

    public Provider() {
        mActions = new HashMap<>();
        registerActions();
    }

    protected void registerAction(String actionName, Action action) {
        if (mActions.containsKey(actionName)) {
            Log.e(TAG, "LightRouter/registerAction(): action has been registered!");
            return;
        }
        mActions.put(actionName, action);
    }

    public Action findAction(String actionName) {
        return mActions.get(actionName);
    }

    protected abstract void registerActions();
}
