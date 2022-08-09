package studyCafeKIOSK.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
	private int ticketId;
	private int ticketType;
	private int ticketHour;
	private int ticketPrice;
	private int ticketTimePrice;
}
