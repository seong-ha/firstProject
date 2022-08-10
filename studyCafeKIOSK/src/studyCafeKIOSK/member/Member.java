package studyCafeKIOSK.member;

public class Member {
	private String memberId;
	private String memberName;
	private String memberPw;
	private String phone;
	private String regDate;
	private int memberType;
	private String ticketTimes;
	private String onedayTimes;
	
	public Member() {

	}

	public Member(String memberId, String memberName, String memberPw, String phone, String regDate, int memberType,
			String ticketTimes, String onedayTimes) {
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPw = memberPw;
		this.phone = phone;
		this.regDate = regDate;
		this.memberType = memberType;
		this.ticketTimes = ticketTimes;
		this.onedayTimes = onedayTimes;
	}

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

	public String getTicketTimes() {
		return ticketTimes;
	}

	public String getOnedayTimes() {
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

	public void setTicketTimes(String ticketTimes) {
		this.ticketTimes = ticketTimes;
	}

	public void setOnedayTimes(String onedayTimes) {
		this.onedayTimes = onedayTimes;
	}
	
}
