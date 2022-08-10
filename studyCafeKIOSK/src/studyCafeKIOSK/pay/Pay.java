package studyCafeKIOSK.pay;

public class Pay {
	private int payId; // sequence++
	private String orderId;
	private int payment;
	private int payPrice;
	private String fromMemberId;
	private String toMemberId;

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
