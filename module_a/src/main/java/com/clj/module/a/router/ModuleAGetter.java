package com.clj.module.a.router;

import android.content.Context;

import com.clj.lib.common.RouterApi;
import com.clj.router.LightRouter;
import com.clj.router.RouterRequest;
import com.clj.router.RouterResponse;

/**
 * 模块A通过API协议获取其他模块数据
 */
public class ModuleAGetter {

    /**
     * 跳转到模块B主页
     */
    public static boolean navigateToModuleBUI(Context context, String param) {
        RouterResponse routerResponse = LightRouter.getInstance()
                .router(context, RouterRequest.build()
                        .provider(RouterApi.MODULE_B_PROVIDER)
                        .action(RouterApi.NAVIGATE_TO_MODULE_B_UI)
                        .params(RouterApi.NAVIGATE_TO_MODULE_B_UI_PARAM_1, param));
        return routerResponse != null && routerResponse.isSuccess();
    }
}
