package studyCafeKIOSK.member;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;
import studyCafeKIOSK.order.Order;

public class MemberDAO extends DAO {
	private static MemberDAO memberDAO = null;

	private MemberDAO() {

	}

	public static MemberDAO getInstance() {
		return (memberDAO == null) ? memberDAO = new MemberDAO() : memberDAO;
	}

	// 회원가입.
	public int insertMember(Member member) {
		int result = 0;

		try {
			conn();
			String sql = "insert into member (member_id, member_name, member_pw, phone) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberName());
			pstmt.setString(3, member.getMemberPw());
			pstmt.setString(4, member.getPhone());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	// 로그인
	public Member login(String memberId, String memberPw) {
		Member member = null;

		try {
			conn();
			String sql = "select * from member where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			String rspw = "";
			if (rs.next()) {
				rspw = rs.getString("member_pw");

				if (rspw.equals(memberPw)) {
					member = new Member();
					member.setMemberId(rs.getString("member_id"));
					member.setMemberName(rs.getString("member_name"));
					member.setMemberPw(rs.getString("member_pw"));
					member.setPhone(rs.getString("phone"));
					member.setRegDate(rs.getString("reg_date"));
					member.setMemberType(rs.getInt("member_Type"));
					member.setTicketTimes(rs.getInt("ticket_times"));
					member.setOnedayTimes(rs.getInt("oneday_times"));
				} else {
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {
				System.out.println("등록되지 않은 아이디입니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return member;
	}

	// 비밀번호 재설정 (접속한 아이디로 정보 조회 후 비번 확인 후 바꿀 비번으로 update)
	public int updateMemberPw(String memberId, String memberNowPw, String memberNextPw) {
		int result = 0;

		try {
			conn();
			String selectSql = "select * from member where member_id = ?";
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setString(1, memberId);

			rs = pstmt.executeQuery();

			if (rs.getString("member_pw").equals(memberNowPw)) {
				String updateSql = "update member set member_pw = ? where member_id = ?";
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setString(1, memberNextPw);
				pstmt.setString(2, memberId);

				result = pstmt.executeUpdate();
			} else {
				System.out.println("비밀번호가 틀렸습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return 0;
	}

	// 회원 티켓별 시간 추가.
	public int updateTimesPlus(Order order) {
		int result = 0;
		String sql = "";

		try {
			conn();
			if (order.getTicketType() == 1) {
				sql = "update member set oneday_times = oneday_times + (? * 60) where member_id = ?";
			} else if (order.getTicketType() == 3) {
				sql = "update member set ticket_times = ticket_times + (? * 60) where member_id = ?";
			} else {
				System.out.println("티켓타입이 1(1회권)이나 2(정액권)가 아닙니다.");
				return 0;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getTicketHour());
			pstmt.setString(2, order.getMemberId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}

	public int updateTimesMinus(Order order) {
		int result = 0;
		String sql = "";

		try {
			conn();
			if (order.getTicketType() == 2) {
				sql = "update member set ticket_times = ticket_times - (? * 60) where member_id = ?";
			} else {
				System.out.println("티켓타입이 1(1회권)이나 2(정액권)가 아닙니다.");
				return 0;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, order.getTicketHour());
			pstmt.setString(2, order.getMemberId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

		return result;
	}
}
