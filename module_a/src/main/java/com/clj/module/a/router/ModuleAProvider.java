package com.clj.module.a.router;

import com.clj.lib.common.RouterApi;
import com.clj.router.Provider;

/**
 * 注册模块A对其他模块提供API
 */
public class ModuleAProvider extends Provider {

    @Override
    protected void registerActions() {
        registerAction(RouterApi.GET_USER_ID, new GetUserIdAction());
    }
}
