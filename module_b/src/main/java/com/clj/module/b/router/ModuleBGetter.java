package com.clj.module.b.router;

import android.content.Context;

import com.clj.lib.common.RouterApi;
import com.clj.router.LightRouter;
import com.clj.router.RouterRequest;
import com.clj.router.RouterResponse;

/**
 * 模块B通过API协议获取其他模块数据
 */
public class ModuleBGetter {

    /**
     * 获取用户ID
     */
    public static int getUserId(Context context) {
        RouterResponse routerResponse = LightRouter.getInstance()
                .router(context,
                        RouterRequest.build()
                                .provider(RouterApi.MODULE_A_PROVIDER)
                                .action(RouterApi.GET_USER_ID));
        if (routerResponse != null && routerResponse.isSuccess()) {
            return (int) routerResponse.getData();
        }
        return -1;
    }
}
