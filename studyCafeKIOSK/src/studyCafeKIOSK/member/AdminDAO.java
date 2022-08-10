package studyCafeKIOSK.member;

import studyCafeKIOSK.common.DAO;

public class AdminDAO extends DAO {
	private static AdminDAO adminDAO = null;
	
	private AdminDAO() {
		
	}
	
	public static AdminDAO getInstance() {
		return (adminDAO == null) ? adminDAO = new AdminDAO() : adminDAO;
	}
	
	// 카드 / 현금 영수증 임의 결제
	public int updatePay() {
		int result = 0;
		
		try {
			conn();
			String sql = "update member (member_id, member_name, member_pw, phone) values (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "");
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return result;
	}
}
