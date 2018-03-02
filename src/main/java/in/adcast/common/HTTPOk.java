package in.adcast.common;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HTTPOk{
	private String status;
	private String message;
	private String data; 
	private String data1;
	private String data2;
	private String updatedDateTime;
	
	public HTTPOk(){
		
	}
	public HTTPOk(final Status status,final String message) {
		this.setMessage(message);
		this.setStatus(status.toString());
	}
	
	public HTTPOk(final Status status,final String message,final String data) {
		this.setMessage(message);
		this.setStatus(status.toString());
		this.setData(data);
	}
	
	public HTTPOk(final Status status,final String message,final String data,final String data1) {
		this.setMessage(message);
		this.setStatus(status.toString());
		this.setData(data);
		this.setData1(data1);
	}
	public HTTPOk(final Status status,final String message,final String data,final String data1,final String data2) {
		this.setMessage(message);
		this.setStatus(status.toString());
		this.setData(data);
		this.setData1(data1);
		this.setData2(data2);
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData1() {
		return data1;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	public String getData2() {
		return data2;
	}
	public String getUpdatedDateTime() {
		return updatedDateTime;
	}
	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}
	
	
	
}
