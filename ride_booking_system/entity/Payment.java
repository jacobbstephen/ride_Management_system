package ride_booking_system.entity;

import java.time.LocalDateTime;

public class Payment {
	private String paymentMethod;
	private double amount;
	private LocalDateTime paidAt;
	private boolean isDone;
	private int id;

	public Payment(String paymentMethod, double amount) {
		super();
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.paidAt = LocalDateTime.now();
		this.isDone = true;
	}
	public Payment(){
		
	}

	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public double getAmount() {
		return amount;
	}
	public LocalDateTime getPaidAt() {
		return paidAt;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setFields(int id, String paymentMethod, double amount, LocalDateTime paidAt, boolean isDone){
		this.amount = amount;
		this.id = id;
		this.isDone = isDone;
		this.paidAt = paidAt;
		this.paymentMethod = paymentMethod;
	}
}
