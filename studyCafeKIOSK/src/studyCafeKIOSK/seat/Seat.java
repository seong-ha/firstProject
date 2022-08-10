package studyCafeKIOSK.seat;

public class Seat {
	private int seatNo;
	private String memberId;
	private String startTime;
	private String finishTime;
	private String leftTime;

	public Seat() {

	}

	public Seat(int seatNo, String memberId, String startTime, String finishTime, String leftTime) {
		this.seatNo = seatNo;
		this.memberId = memberId;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.leftTime = leftTime;
	}

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

	public String getLeftTime() {
		return leftTime;
	}

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

	public void setLeftTime(String leftTime) {
		this.leftTime = leftTime;
	}

}
