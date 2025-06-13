package ride_booking_system.entity;

import java.time.LocalDateTime;

public class Payment {
	private String paymentMethod;
	private double amount;
	private LocalDateTime paidAt;
	private boolean isDone;
	
	public Payment(String paymentMethod, double amount) {
		super();
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.paidAt = LocalDateTime.now();
		this.isDone = true;
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
}
