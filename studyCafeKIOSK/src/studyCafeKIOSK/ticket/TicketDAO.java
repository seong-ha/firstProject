package studyCafeKIOSK.ticket;

public class TicketDAO {
	
	private static TicketDAO ticketDAO = null;

	private TicketDAO() {

	}

	public static TicketDAO getInstance() {
		return (ticketDAO == null) ? ticketDAO = new TicketDAO() : ticketDAO;
	}
}
