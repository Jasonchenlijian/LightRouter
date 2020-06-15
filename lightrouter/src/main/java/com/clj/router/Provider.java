package com.clj.router;


import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由提供者
 */
public abstract class Provider {

    private static final String TAG = Provider.class.getSimpleName();
    /**
     * 路由提供者的所有Action集合
     */
    private ConcurrentHashMap<String, Action> mActions;

    public Provider() {
        mActions = new ConcurrentHashMap<>();
        registerActions();
    }

    /**
     * 注册一个Action到路由提供者中
     *
     * @param actionName Action键名
     * @param action     Action对象
     */
    protected void registerAction(String actionName, Action action) {
        if (mActions.containsKey(actionName)) {
            Log.e(TAG, "LightRouter/registerAction(): action has been registered!");
            return;
        }
        mActions.put(actionName, action);
    }

    /**
     * 根据Action键名找到对应的Action对象
     *
     * @param actionName Action键名
     * @return Action对象
     */
    public Action findAction(String actionName) {
        return mActions.get(actionName);
    }

    protected abstract void registerActions();
}
