package com.sdd.inventory.viewmodel.backend;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import com.sdd.inventory.domain.Muser;

public class MainVm {
	
	private Session zkSession = Sessions.getCurrent();

	private Muser objuser;
	
	@Wire
	private Div divContent;
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		objuser = (Muser) zkSession.getAttribute("oUser");
		if(objuser ==null) {
			Executions.sendRedirect("../login.zul");
		} else {
			doNav("/view/backend/home.zul");
		}
		
	}
	
	@Command
	public void doNav(@BindingParam("page") String page) {
		divContent.getChildren().clear();
		Executions.createComponents(page, divContent, null);
	}
	
}
