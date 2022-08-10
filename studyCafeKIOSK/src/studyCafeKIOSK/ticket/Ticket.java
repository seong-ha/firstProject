package studyCafeKIOSK.ticket;

public class Ticket {
	private int ticketId;
	private int ticketType;
	private int ticketHour;
	private int ticketPrice;
	private int ticketTimePrice;

	public int getTicketId() {
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

	public int getTicketTimePrice() {
		return ticketTimePrice;
	}

	public void setTicketId(int ticketId) {
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

	public void setTicketTimePrice(int ticketTimePrice) {
		this.ticketTimePrice = ticketTimePrice;
	}

}
