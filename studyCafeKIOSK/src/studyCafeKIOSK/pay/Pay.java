package studyCafeKIOSK.pay;

public class Pay {
	private String payId; // to_number(to_char(sysdate, 'yyyymmddhh24mi')) + 1
	private int payment;
	private int payPrice;
	private String fromAccountId;
	private String toAccountId;

	public String getPayId() {
		return payId;
	}

	public int getPayment() {
		return payment;
	}

	public int getPayPrice() {
		return payPrice;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public void setPayPrice(int payPrice) {
		this.payPrice = payPrice;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

}
