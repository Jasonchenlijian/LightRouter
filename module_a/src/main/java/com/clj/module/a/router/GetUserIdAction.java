package com.clj.module.a.router;

import android.content.Context;

import com.clj.module.a.ui.ModuleAActivity;
import com.clj.router.Action;

import java.util.HashMap;

/**
 * 对其他模块暴露“获取用户ID”的实现
 */
public class GetUserIdAction extends Action {

    @Override
    public Object startAction(Context context, HashMap<String, Object> requestData) {
        return ModuleAActivity.USER_ID;
    }
}
