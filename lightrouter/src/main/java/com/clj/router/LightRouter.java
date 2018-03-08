package com.clj.router;

import android.content.Context;
import android.util.Log;

import java.util.HashMap;

public class LightRouter {

    private static final String TAG = LightRouter.class.getSimpleName();

    private static volatile LightRouter mInstance = null;
    private HashMap<String, Provider> mProviders = null;

    private LightRouter() {
        mProviders = new HashMap<>();
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

    public void registerProvider(String providerName, Provider provider) {
        if (mProviders.containsKey(providerName)) {
            Log.e(TAG, "LightRouter/registerProvider(): this provider has been registered!");
            return;
        }
        mProviders.put(providerName, provider);
    }

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

    private Provider findRequestProvider(RouterRequest routerRequest) {
        return mProviders.get(routerRequest.getProvider());
    }

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
