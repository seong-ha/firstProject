package studyCafeKIOSK.ticket;

public class Ticket {
	private String ticketId;
	private int ticketType;
	private int ticketHour;
	private int ticketPrice;

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
