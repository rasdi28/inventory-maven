package com.sdd.inventory.viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

public class mainVm {

	
	@Wire
	private Div divContent;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		doNav("/view/home.zul");
	}
	
	@Command
	public void doNav(@BindingParam("page") String page) {
		divContent.getChildren().clear();
		Executions.createComponents(page, divContent, null);
	}
	
	
	
	
}
