package com.clj.router;

import java.util.HashMap;

public class RouterRequest {

    private String provider;
    private String action;
    private HashMap<String, Object> params;

    private RouterRequest() {
        this.params = new HashMap<>();
    }

    public static RouterRequest build() {
        return new RouterRequest();
    }


    public RouterRequest provider(String provider) {
        this.provider = provider;
        return this;
    }

    public RouterRequest action(String action) {
        this.action = action;
        return this;
    }

    public RouterRequest params(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    public String getProvider() {
        return this.provider;
    }

    public String getAction() {
        return this.action;
    }

    public HashMap<String, Object> getParams() {
        return this.params;
    }


}
