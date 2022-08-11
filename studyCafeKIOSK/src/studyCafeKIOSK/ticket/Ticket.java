package studyCafeKIOSK.ticket;

public class Ticket {
	private String ticketId; // pk
	private int ticketType; // not null 1:1회권 2:정액권이용(시간) 3: 정액권충전 4: 연장(시간과 금액)
	private int ticketHour; // not null
	private int ticketPrice; // 

	public String getTicketId() {
		return ticketId;
	}

	public int getTicketType() {
		return ticketType;
	}

	public int getTicketHour() {
		return ticketHour;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}

	public void setTicketHour(int ticketHour) {
		this.ticketHour = ticketHour;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
