package com.clj.module.b.router;

import com.clj.lib.common.RouterApi;
import com.clj.router.Provider;

/**
 * 注册模块B对其他模块提供API
 */

public class ModuleBProvider extends Provider {
    @Override
    protected void registerActions() {
        registerAction(RouterApi.NAVIGATE_TO_MODULE_B_UI, new NavigateModuleBUIAction());
    }
}
