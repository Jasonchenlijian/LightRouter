package com.clj.router;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由分发中心
 */
public class LightRouter {

    private static final String TAG = LightRouter.class.getSimpleName();

    private static volatile LightRouter mInstance = null;
    private ConcurrentHashMap<String, Provider> mProviders = null;

    private LightRouter() {
        mProviders = new ConcurrentHashMap<>();
    }

    public static LightRouter getInstance() {
        if (mInstance == null) {
            synchronized (LightRouter.class) {
                if (mInstance == null) {
                    Log.e("", "LightRouter/getInstance(): init!");
                    mInstance = new LightRouter();
                }
            }
        }
        return mInstance;
    }

    /**
     * 将路由提供者注册到路由分发中心
     *
     * @param providerName 路由提供者的键名
     * @param provider     路由提供者对象
     */
    public void registerProvider(String providerName, Provider provider) {
        if (mProviders.containsKey(providerName)) {
            Log.e(TAG, "LightRouter/registerProvider(): this provider has been registered!");
            return;
        }
        mProviders.put(providerName, provider);
    }

    /**
     * 路由分发中心根据路由请求对象，依次找到对应的路由提供者和其对应的Action，最终执行Action。
     *
     * @param c             上下文
     * @param routerRequest 路由请求对象
     * @return 路由执行结果
     */
    public RouterResponse router(Context c, RouterRequest routerRequest) {
        RouterResponse routerResponse = new RouterResponse();
        Provider provider = findRequestProvider(routerRequest);
        if (provider != null) {
            Action action = findRequestAction(routerRequest, provider);
            if (action != null) {
                Object object = action.startAction(c, routerRequest.getParams());
                routerResponse.setResult(RouterResponse.SUCCESS_CODE, RouterResponse.SUCCESS_DESC, object);
            } else {
                routerResponse.setResult(RouterResponse.FAIL_CODE, RouterResponse.FAIL_DESC, RouterResponse.FAIL_DESCRIPTION_ACTION);
            }
        } else {
            routerResponse.setResult(RouterResponse.FAIL_CODE, RouterResponse.FAIL_DESC, RouterResponse.FAIL_DESCRIPTION_PROVIDER);
        }
        return routerResponse;
    }

    /**
     * 根据{@link RouterRequest}对象的provider键名，找到Provider
     *
     * @param routerRequest 路由请求对象，包含provider键名
     * @return 路由分发中心是否存在该键名对应的Provider
     */
    private Provider findRequestProvider(RouterRequest routerRequest) {
        return mProviders.get(routerRequest.getProvider());
    }

    /**
     * 根据{@link RouterRequest}对象的action键名，找到Action
     *
     * @param routerRequest  路由请求对象，包含action键名
     * @param targetProvider 目标Provider
     * @return 目标Provider中是否存在该键名对应的Action
     */
    private Action findRequestAction(RouterRequest routerRequest, Provider targetProvider) {
        if (targetProvider != null) {
            Action targetAction = targetProvider.findAction(routerRequest.getAction());
            if (targetAction != null) {
                return targetAction;
            }
        }
        return null;
    }


}
