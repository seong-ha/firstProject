package studyCafeKIOSK.member;

public class Member {
	private String memberId;
	private String memberName;
	private String memberPw;
	private String phone;
	private String regDate;
	private int memberType;
	private int ticketTimes;
	private int onedayTimes;

	public String getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public String getPhone() {
		return phone;
	}

	public String getRegDate() {
		return regDate;
	}

	public int getMemberType() {
		return memberType;
	}

	public int getTicketTimes() {
		return ticketTimes;
	}

	public int getOnedayTimes() {
		return onedayTimes;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setMemberType(int memberType) {
		this.memberType = memberType;
	}

	public void setTicketTimes(int ticketTimes) {
		this.ticketTimes = ticketTimes;
	}

	public void setOnedayTimes(int onedayTimes) {
		this.onedayTimes = onedayTimes;
	}
	
}
