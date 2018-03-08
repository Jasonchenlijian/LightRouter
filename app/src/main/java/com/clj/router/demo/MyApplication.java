package com.clj.router.demo;

import android.app.Application;

import com.clj.lib.common.RouterApi;
import com.clj.module.a.router.ModuleAProvider;
import com.clj.module.b.router.ModuleBProvider;
import com.clj.router.LightRouter;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册每个模块的协议提供者
        LightRouter.getInstance().registerProvider(RouterApi.MODULE_A_PROVIDER, new ModuleAProvider());
        LightRouter.getInstance().registerProvider(RouterApi.MODULE_B_PROVIDER, new ModuleBProvider());

    }
}
