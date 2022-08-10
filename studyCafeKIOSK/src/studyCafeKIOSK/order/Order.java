package studyCafeKIOSK.order;

public class Order {
	private String orderId; // dual로 sysdate 받아와서 + sequence++ 해서 insert
	private String memberId;
	private int seatNo;
	private String orderTime; // dual로 sysdate 받아와서 넣기.
	private int payment;
	private int ticketType;
	private int ticketHour;
	private int ticketPrice;

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

	public int getPayment() {
		return payment;
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

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public int getTicketHour() {
		return ticketHour;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketHour(int ticketHour) {
		this.ticketHour = ticketHour;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTicketType() {
		return ticketType;
	}

	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}

}
