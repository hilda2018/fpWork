package com.novaone.core;

import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.novaone.common.frammodule.DAOModule;
import com.novaone.common.frammodule.ServiceModule;

/**
 * 此类用于不同项目扩展giuce<br>
 * 需要在具体使用的项目中重写此类即可<br>
 *     使用包com.novaone.core
 */
public class GiuceInjectorOther {

	/**
	 * 根据项目需要在此处注入dao层
	 * @param daoModule
	 */
	public static void addDaoInject(Module daoModule) {
		DAOModule dm = (DAOModule) daoModule;
//		dm.addBind("ContactsDao", ContactsDao.class);
//		dm.addBind("SupplierProductDao", SupplierProductInjectDao.class);
//		dm.addBind("saleInfoDao", SaleInfoDao.class);
//		dm.addBind("logisticstrackingDao", LogisticstrackingDao.class);
//		dm.addBind("buyerInspectionReportDao", InspectionReportDao.class);
//		dm.addBind("thirdInspectionReportDao", InspectionReportDao.class);
//		dm.addBind("SupplierPaymentDao", SupplierPaymentInjectDao.class);//供应商收款列表根据sellerid过滤
//		dm.addBind("ConfirmAdvancesSplitListDao", SupplierPaymentInjectDao.class);//预付款拆分确认列表根据sellerid过滤
////		dm.addBind("apply_register", ApplyRegisterDao.class);
////		dm.addBind("apply_forbusiness", ApplyForBusinessDao.class);
//		dm.addBind("supplierConfirmDao", SupplierConfirmDao.class);//结算确认根据sellerid过滤
//		dm.addBind("bankDao", BankInjectDao.class);//用户银行根据userID过滤
//		dm.addBind("ocrmainlistDao", UploadListDao.class);//OCR上传列表根据creater过滤
//		dm.addBind("ActualReceiveUnitListDao", ActualReceiveUnitInjectDao.class);//实际收货单位
//		dm.addBind("tablecheckDao", TableCheckDao.class);//单表校验增加
	}

	/**
	 * 根据项目需要在此注入service层
	 * @param serviceModule
	 */
	public static void addServiceInject(Module serviceModule) {
		ServiceModule sm = (ServiceModule)serviceModule;
	}

	/**
	 * 根据项目需要在此注入其它接口
	 * @param gic GuiceServletContextListener
	 */
	public static void addOtherInjectModule(GuiceServletContextListener gic) {

	}

}