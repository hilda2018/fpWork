package com.novaone.common.frammodule;

import com.google.inject.Binder;
import com.google.inject.Module;

public class ClientDaoModule implements Module{

	@Override
	public void configure(Binder binder) {
	//此处填写自 己项目 中需入注入的Dao或service， 如下
	//注入dao：
//	binder. bind(ContactsDao. class) . to(ContactsDaoImpl. class) ;
//	binder. bind(SupplierProductInjectDao. class) . to(SupplierProductInjectDaoImpl. class) ;
//	binder. bind(SaleInfoDao. class) . to(SaleInfoDaoImpl. class) ;
//	binder. bind(LogisticstrackingDao. class) . to(LogisticstrackingDaoImpl. class) ;
//	binder. bind(InspectionReportDao. class) . to(InspectionReportDaoImpl. class) ;
//	binder. bind(SupplierPaymentInjectDao. class) . to(SupplierPaymentInjectDaoImpl. class) ;
//	binder. bind(SupplierConfirmDao. class) . to(SupplierConfirmDaoImpl. class) ;
//	binder. bind(BankInjectDao. class) . to(BankInjectDaoImpl. class) ;
//	binder. bind(UploadListDao. class) . to(UploadListDaoImpl. class) ;
//	binder. bind(ActualReceiveUnitInjectDao.class).to(ActualReceiveUnitInjectDaoImpl.class);
//	binder. bind(TableCheckDao.class).to(TableCheckDaoImpl.class);
	//注入service：
	//binder. bind(ProductService. class) . to(ProductServiceImpl. class) ;
	}
}
