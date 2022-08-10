package studyCafeKIOSK.order;

public class Order {
	private String orderId;
	private String memberId;
	private int seatNo;
	private String orderTime;
	private String orderPrice;
	private int payment;
	private String orderedTicketId;

	public String getOrderId() {
		return orderId;
	}

	public String getMemberId() {
		return memberId;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public int getPayment() {
		return payment;
	}

	public String getOrderedTicketId() {
		return orderedTicketId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public void setOrderedTicketId(String orderedTicketId) {
		this.orderedTicketId = orderedTicketId;
	}

}
