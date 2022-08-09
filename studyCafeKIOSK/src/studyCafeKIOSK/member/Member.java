package studyCafeKIOSK.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String memberId;
	private String memberName;
	private String memberPw;
	private String Phone;
	private String regDate;
	private int memberType;
	private String ticketTimes;
	private String onedayTimes;
}
