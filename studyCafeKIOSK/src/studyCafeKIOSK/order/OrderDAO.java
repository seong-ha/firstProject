package studyCafeKIOSK.order;

import studyCafeKIOSK.common.DAO;

public class OrderDAO extends DAO {

	private static OrderDAO orderDAO = null;

	private OrderDAO() {

	}

	public static OrderDAO getInstance() {
		return (orderDAO == null) ? orderDAO = new OrderDAO() : orderDAO;
	}

	
}
