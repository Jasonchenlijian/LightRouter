package com.clj.module.b.router;

import android.content.Context;
import android.content.Intent;

import com.clj.lib.common.RouterApi;
import com.clj.module.b.ui.ModuleBActivity;
import com.clj.router.Action;

import java.util.HashMap;

/**
 * 对其他模块暴露“跳转到模块B首页”的接口
 */
public class NavigateModuleBUIAction extends Action {

    @Override
    public Object startAction(Context context, HashMap<String, Object> requestData) {
        String param = (String) requestData.get(RouterApi.NAVIGATE_TO_MODULE_B_UI_PARAM_1);
        Intent intent = new Intent(context, ModuleBActivity.class);
        intent.putExtra(ModuleBActivity.KEY_INTENT_PARAM, param);
        context.startActivity(intent);
        return null;
    }
}
