package com.sdd.inventory.viewmodel.backend;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;

import com.sdd.inventory.DAO.TorderDAO;
import com.sdd.inventory.domain.Mproduct;
import com.sdd.inventory.domain.Torder;

public class OrderVm {

	private List<Torder> Listorder;
	private Torder objorder;
	private BigDecimal total;

	private List<Torder> Listkeranjang = new ArrayList<Torder>();
	private List<Mproduct> Listproduct = new ArrayList<>();

	private Map<Integer, Mproduct> mapProduct = new HashMap<Integer, Mproduct>();

	private String productname;
	private Date ordertime;
	private BigDecimal totalprice;

	@Wire
	private Grid grid;

	@Wire
	private Grid gridkeranjang;

	@Wire
	private Button btnSubmit;

	@Wire
	private Combobox cbproduct;

	@Wire
	private Button btnDelete;

	Mproduct objproduct;

	@AfterCompose
	@NotifyChange("*")
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);

		doReset();
		gridkeranjang.setRowRenderer(new RowRenderer<Torder>() {

			@Override
			public void render(Row row, Torder data, int index) throws Exception {
				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getMproduct().getProductname()));
				row.appendChild(new Label(String.valueOf(data.getOrderqty())));
				row.appendChild(new Label(new SimpleDateFormat("dd-MM-yyyy HH-mm").format(data.getOrdertime())));
				row.appendChild(new Label(NumberFormat.getInstance().format(data.getTotalprice())));
			}

		});

		grid.setRowRenderer(new RowRenderer<Torder>() {

			@Override
			public void render(Row row, Torder data, int index) throws Exception {
				row.appendChild(new Label(String.valueOf(++index)));
				row.appendChild(new Label(data.getMproduct().getProductname()));
				row.appendChild(new Label(String.valueOf(data.getOrderqty())));
				row.appendChild(new Label(new SimpleDateFormat("dd-MM-yyyy HH-mm").format(data.getOrdertime())));
				row.appendChild(new Label(NumberFormat.getInstance().format(data.getTotalprice())));
			}

		});

		Button btnDel = new Button("Delete");
		btnDel.addEventListener(Events.ON_OK, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) throws Exception {
				Messagebox.show("apakah anda akan menghapus?", "Konfirmasi", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener<Event>() {

							@Override
							public void onEvent(Event event) throws Exception {
								if (event.getName().equals("onOK")) {
									try {
										new TorderDAO().delete(objorder);
										Messagebox.show("Data Berhasil Dihapus");
										doReset();
										BindUtils.postNotifyChange(null, null, OrderVm.this, "*");
									
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						});
			}
		});
		
		
	}

	@Command
	@NotifyChange("*")
	public void doReset() {
		ordertime = new Date();
		objorder = new Torder();
		Listorder = new TorderDAO().listAll();
		doRefresh();
	}

	public void doRefresh() {

	}

	public List<Torder> getListorder() {
		return Listorder;
	}

	public Torder getObjorder() {
		return objorder;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public List<Torder> getListkeranjang() {
		return Listkeranjang;
	}

	public List<Mproduct> getListproduct() {
		return Listproduct;
	}

	public Map<Integer, Mproduct> getMapProduct() {
		return mapProduct;
	}

	public String getProductname() {
		return productname;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setListorder(List<Torder> listorder) {
		Listorder = listorder;
	}

	public void setObjorder(Torder objorder) {
		this.objorder = objorder;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setListkeranjang(List<Torder> listkeranjang) {
		Listkeranjang = listkeranjang;
	}

	public void setListproduct(List<Mproduct> listproduct) {
		Listproduct = listproduct;
	}

	public void setMapProduct(Map<Integer, Mproduct> mapProduct) {
		this.mapProduct = mapProduct;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

}
