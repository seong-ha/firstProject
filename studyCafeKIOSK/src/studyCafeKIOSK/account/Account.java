package studyCafeKIOSK.account;

public class Account {
	private String accountId;
	private String memberId;
	private int balance;

	public String getAccountId() {
		return accountId;
	}

	public String getMemberId() {
		return memberId;
	}

	public int getBalance() {
		return balance;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
