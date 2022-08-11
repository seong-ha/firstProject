package studyCafeKIOSK.seat;

public class Seat {
	private int seatNo; // pk
	private String memberId; // reference member(member_id)
	private String startTime; // date
	private String finishTime; // date
//	private String leftTime;  이건 그때그때 쿼리로 가져와야함. sysdate - finishTime

	public int getSeatNo() {
		return seatNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

//	public String getLeftTime() {
//		return leftTime;
//	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

//	public void setLeftTime(String leftTime) {
//		this.leftTime = leftTime;
//	}

}
