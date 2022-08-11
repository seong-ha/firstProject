package studyCafeKIOSK.pay;

public class Pay {
	private int payId; // sequence++ // pk
	private String orderId; // references orders(order_id)
	private int payment;
	private int payPrice;
	private String fromMemberId; // references member(member_id)
	private String toMemberId; // references member(member_id)

	public int getPayId() {
		return payId;
	}

	public int getPayment() {
		return payment;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public String getFromMemberId() {
		return fromMemberId;
	}

	public String getToMemberId() {
		return toMemberId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public void setFromMemberId(String fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public void setToMemberId(String toMemberId) {
		this.toMemberId = toMemberId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
