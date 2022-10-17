package hello.core.order;

public interface OrderService {

    Order createOrder(int memberId, String productName, int productPrice);

}
