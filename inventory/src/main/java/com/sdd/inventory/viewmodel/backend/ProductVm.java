package com.sdd.inventory.viewmodel.backend;

import java.text.NumberFormat;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.sdd.inventory.DAO.MproductDAO;
import com.sdd.inventory.domain.Mproduct;


public class ProductVm {
	
	private List<Mproduct> objListproduct;
	
	private Mproduct objproduct;
	
	@Wire
	private Button btnSubmit;
	
	@Wire
	private Button btnDelete;
	
	@Wire
	private Grid grid;
	
	private String productname;
	
	@AfterCompose
	@NotifyChange("*")
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		doReset();
		
		grid.setRowRenderer(new RowRenderer<Mproduct>() {

			@Override
			public void render(Row row, Mproduct data, int index) throws Exception {
				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getProductname()));
				row.appendChild(new Label(String.valueOf(data.getProductstock())));
				row.appendChild(new Label(NumberFormat.getInstance().format(data.getProductprice())));
				
				Button btnEdit = new Button("Edit");
				btnEdit.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						objproduct = data;
						btnSubmit.setLabel("Update");
						BindUtils.postNotifyChange(null, null, ProductVm.this, "objproduct");
						btnDelete.setDisabled(false);
					}
				});
				
				Button btnDel = new Button("Delete");
				btnDel.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
					new MproductDAO().delete(data);
					doReset();
						
					}
				});
				
				Hlayout hlayout = new Hlayout();
				hlayout.appendChild(btnEdit);
				hlayout.appendChild(btnDel);
				row.appendChild(hlayout);
			}
		});
		
		
		
	}
	
	@Command
	@NotifyChange("*")
	public void doReset() {
		productname = null;
		objproduct = new Mproduct();
		objListproduct = new MproductDAO().listAll();
		doRefresh();
		btnSubmit.setLabel("submit");
		btnDelete.setDisabled(true);
		
	}
	
	public void doRefresh() {
		grid.setModel(new ListModelList<Mproduct>(objListproduct));
	}
	
	@Command
	@NotifyChange("*")
	public void doSearch() {
		if(productname !=null && productname.trim().length()>0) {
			objListproduct = new MproductDAO().getByProductname(productname.trim());
			doRefresh();
		}
	}
	
	
	@Command
	@NotifyChange("*")
	public void doSubmit() {
		try {
			new MproductDAO().save(objproduct);
			Messagebox.show("Data Berhasil ditambahkan");
			
		} catch (Exception e) {
			Messagebox.show(e.getMessage());
			e.printStackTrace();
		}
		doReset();
	}
	
	@NotifyChange("*")
	public void doDelete() {
		try {
			new MproductDAO().delete(objproduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public List<Mproduct> getObjListproduct() {
		return objListproduct;
	}

	public Mproduct getObjproduct() {
		return objproduct;
	}

	public void setObjListproduct(List<Mproduct> objListproduct) {
		this.objListproduct = objListproduct;
	}

	public void setObjproduct(Mproduct objproduct) {
		this.objproduct = objproduct;
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public Grid getGrid() {
		return grid;
	}

	public String getProductname() {
		return productname;
	}

	public void setBtnSubmit(Button btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	

}
