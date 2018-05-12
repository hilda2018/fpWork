package com.fresh.core;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.struts2.Struts2GuicePluginModule;
import com.nova.frame.transaction.TransactionModule;

/**
 * guice全局控制类
 * 
 * @author <a href="mailto:liguotao@novacloud.com">ligt</a>
 * @date 2012-11-18下午5:23:22
 * @version Revision: 1.0
 */
public class GuiceInjectorContext extends GuiceServletContextListener {
	private static Injector injector;

	@Override
	public Injector getInjector() {
		if (injector == null) {
			injector = Guice.createInjector(new Struts2GuicePluginModule(),
					new ServletModule() {
						@Override
						protected void configureServlets() {
							// Struts 2 setup
							bind(StrutsPrepareAndExecuteFilter.class).in(
									Singleton.class);
							filter("/*").through(
									StrutsPrepareAndExecuteFilter.class);
							// 初始化系统配置
							SystemContext.initContext();
							install(new TransactionModule());
						}
					});
		}
		return injector;
	}

	/**
	 * 获取实例
	 * 
	 * @param <T>
	 * @param type
	 * @return
	 */
	public static <T> T getInstance(Class<T> type) {
		if (injector == null) {
			injector = new GuiceInjectorContext().getInjector();
		}
		return injector.getInstance(type);
	}
}