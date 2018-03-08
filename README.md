# LightRouter
轻量级的路由框架，可用于组件间数据交互，页面跳转等。

### Maven

	<dependency>
       <groupId>com.clj.router</groupId>
       <artifactId>lightrouter</artifactId>
       <version>1.0.0</version>
	   <type>pom</type>
	</dependency>

### Gradle

	compile 'com.clj.router:lightrouter:1.0.0'

### Jar包

 [LightRouter_1.0.0.jar](https://github.com/Jasonchenlijian/LightRouter/raw/master/LightRouter_1.0.0.jar)


## 如何使用

- #### 首先在全局基础库中定义所有协议命名及参数

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

- #### 在全局APPlicaiton中注册所有接口提供者

		public class MyApplication extends Application {
		
		    @Override
		    public void onCreate() {
		        super.onCreate();
		
		        // 注册每个模块的协议提供者
		        LightRouter.getInstance().registerProvider(RouterApi.MODULE_A_PROVIDER, new ModuleAProvider());
		        LightRouter.getInstance().registerProvider(RouterApi.MODULE_B_PROVIDER, new ModuleBProvider());
		
		    }
		}



- ## 范例一：通过协议获取其他模块中的数据

- ##### 步骤一： 在模块A中定义某个接口

		public class GetUserIdAction extends Action {
		
		    @Override
		    public Object startAction(Context context, HashMap<String, Object> requestData) {
		        return ModuleAActivity.USER_ID;
		    }
		}

- ##### 步骤二： 在模块A中注册所有定义的接口

		public class ModuleAProvider extends Provider {
		
		    @Override
		    protected void registerActions() {
		        registerAction(RouterApi.GET_USER_ID, new GetUserIdAction());
		    }
		}

- ##### 步骤三：在模块B中通过指定协议获取其他某块数据

		public class ModuleBGetter {
		
			// 获取用户ID
		    public static int getUserId(Context context) {
		        RouterResponse routerResponse = LightRouter.getInstance()
		                .router(context, RouterRequest.build()
		                        .provider(RouterApi.MODULE_A_PROVIDER)
		                        .action(RouterApi.GET_USER_ID));
		        if (routerResponse != null && routerResponse.isSuccess()) {
		            return (int) routerResponse.getData();
		        }
		        return -1;
		    }
		}

- ## 范例二：通过协议进行页面间的跳转

- ##### 步骤一： 在模块B中定义某个接口

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

- ##### 步骤二： 在模块B中注册所有定义的接口

		public class ModuleBProvider extends Provider {
		    @Override
		    protected void registerActions() {
		        registerAction(RouterApi.NAVIGATE_TO_MODULE_B_UI, new NavigateModuleBUIAction());
		    }
		}

- ##### 步骤三：在模块B中通过指定协议获取其他某块数据

		public class ModuleAGetter {
		    // 跳转到模块B首页
		    public static boolean navigateToModuleBUI(Context context, String param) {
		        RouterResponse routerResponse = LightRouter.getInstance()
		                .router(context, RouterRequest.build()
		                        .provider(RouterApi.MODULE_B_PROVIDER)
		                        .action(RouterApi.NAVIGATE_TO_MODULE_B_UI)
		                        .params(RouterApi.NAVIGATE_TO_MODULE_B_UI_PARAM_1, param));
		        return routerResponse != null && routerResponse.isSuccess();
		    }
		}
	

## 设计思路

在一个项目进行解耦或者组件化的过程中，一定会需要改变原有的模块间的通信方式。原有的模块之间相互依赖的方式要取消，最简单的目标是可以让每个模块单独作为一个APP运行，完全移除其他模块的依赖性。

那么有哪些方法可以做到呢？

将代码下沉到基础库中，多模块之间共享代码这种方式，会导致基础库的不断膨胀，是不可取的。而Event总线和广播常用于一对多的场景，作为模块通信的媒介，也并不完全适用。

为了兼顾开发的便利性和协议的约束性，我想到了协议通信。

类似于客户端与服务器之间的网络请求，每一条协议代表一个通信事件，事先将协议定义在公共库中，让通讯双方都能获知，这样既不会造成由于通信的单方擅自修改而使协议失效的情况，也不会增加公共库的负担。比如，模块A通过协议获取模块B的数据，那么模块A就是客户端，模块B就是服务器，他们之间定义的协议就是网络接口。

整个创建过程归纳起来就是这几个步骤：

1. 定义所有协议；
2. 在各个模块中定义所有需要暴露的功能接口，就是范例中的Action；
3. 将各个模块中定义的所有接口注册到每个模块的接口提供者中，就是范例中的Provider；
4. 将各个模块的Provider全部注册到主模块中；
5. 在各个模块中，想要调用某个协议，就可以通过指定的协议去获取，就是范例中的LightRouter对象的router方法。获取的时候需指定2个要素，一个是接口提供者Provider，一个是接口名称，携带参数是可选的，就是范例中的RouterRequest对象。


## 其他
这是一个轻量级的框架，仅仅是对组件化或解耦模块之间的代码做的一点小小的思考。对于跨线程通信和跨进程通信都没有涉及，以及如何以更优的方式将通信数据提供给调用者也是有优化的空间。此框架抛砖引玉，希望与时俱进地提供我们开发者更多的思考方向。


## 联系
如果你有技术方面问题与想法想与我沟通，可以通过下面的方式联系我。

QQ： 1033526540

Email： jasonchenlijian@gmail.com


## License

	   Copyright 2016 chenlijian

	   Licensed under the Apache License, Version 2.0 (the "License");
	   you may not use this file except in compliance with the License.
	   You may obtain a copy of the License at

   		   http://www.apache.org/licenses/LICENSE-2.0

	   Unless required by applicable law or agreed to in writing, software
	   distributed under the License is distributed on an "AS IS" BASIS,
	   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	   See the License for the specific language governing permissions and
	   limitations under the License.