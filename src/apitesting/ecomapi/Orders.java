package apitesting.ecomapi;

import java.util.List;

public class Orders {
private List<OrdersDetails> ordersDetails;

public List<OrdersDetails> getOrders() {
	return ordersDetails;
}

public void setOrders(List<OrdersDetails> ordersDetails) {
	this.ordersDetails = ordersDetails;
}
}
