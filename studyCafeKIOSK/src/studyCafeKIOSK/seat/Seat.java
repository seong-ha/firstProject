package studyCafeKIOSK.seat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seat {
	private int seatNo;
	private String memberId;
	private String startTime;
	private String finishTime;
	private String leftTime;
}
