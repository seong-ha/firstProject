package studyCafeKIOSK.member;

import java.util.ArrayList;
import java.util.List;

import studyCafeKIOSK.common.DAO;

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
	
	// 회원 조회
	public List<Member> findById(String memberId) {
		List<Member> list = new ArrayList<>();
		Member member = null;
		
		try {
			conn();
			String sql = "select * from member where member_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberName(rs.getString("member_name"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setPhone(rs.getString("phone"));
				member.setRegDate(rs.getString("reg_date"));
				member.setMemberType(rs.getInt("member_Type"));
				member.setTicketTimes(rs.getString("ticket_times"));
				member.setMemberId(rs.getString("oneday_times"));
				
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
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
	
}
