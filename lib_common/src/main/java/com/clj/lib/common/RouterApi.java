package com.clj.lib.common;

/**
 * 项目中所有用到的路由协议
 */
public class RouterApi {

    // A模块API提供者
    public static final String MODULE_A_PROVIDER = "com.clj.module.a";
    // B模块API提供者
    public static final String MODULE_B_PROVIDER = "com.clj.module.b";


    // 获取用户ID
    public static final String GET_USER_ID = "getUserId";

    // 跳转到B模块首页
    public static final String NAVIGATE_TO_MODULE_B_UI = "navigateToModuleBUI";
    public static final String NAVIGATE_TO_MODULE_B_UI_PARAM_1 = "navigateToModuleBUI_param_1";

}
