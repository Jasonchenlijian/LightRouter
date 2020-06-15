package com.clj.router;

import android.content.Context;

import java.util.HashMap;

/**
 * 路由执行动作
 */
public abstract class Action {

    /**
     * 执行一个路由动作
     *
     * @param context     上下文
     * @param requestData 执行动作所需的额外参数
     * @return 执行结果
     */
    public abstract Object startAction(Context context, HashMap<String, Object> requestData);

}
