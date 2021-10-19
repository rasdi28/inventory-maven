package com.sdd.inventory.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Messagebox;

import com.sdd.inventory.DAO.MuserDAO;
import com.sdd.inventory.domain.Muser;

public class LoginVm {
	
	private String username;
	private String password;
	
	Muser objuser;
	
	List<Muser> objlistmuser;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		System.out.println("Running LoginVm");
		objlistmuser = new ArrayList<Muser>();
	
		
	}
	
	@Command
	@NotifyChange("*")
	public void doLogin() {
		if(username !=null && password !=null) {
			try {
				Muser obj = new MuserDAO().authentication(username);
				if (obj !=null) {
					if(obj.getPassword().trim().equals(password)) {
						Sessions.getCurrent().setAttribute("oUser", obj);
						Executions.sendRedirect("backend/main.zul");
						
					} else {
						Messagebox.show("invalid Password");
					}
				} else {
					Messagebox.show("invalid user id");
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
