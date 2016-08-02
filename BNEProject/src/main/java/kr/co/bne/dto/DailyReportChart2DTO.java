package kr.co.bne.dto;

public class DailyReportChart2DTO {
	String client_name;
	private String reg_date;
	private int sales;
	int sales_rate;
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public int getSales_rate() {
		return sales_rate;
	}
	public void setSales_rate(int sales_rate) {
		this.sales_rate = sales_rate;
	}
		
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getSales() {
		return sales;
	}
	public void setSales(int sales) {
		this.sales = sales;
	}
}
