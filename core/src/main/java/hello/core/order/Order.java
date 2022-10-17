package hello.core.order;

public class Order {
    private int memberId;
    private String productName;
    private int productPrice;
    private int discountPrice;

    public Order(int memberId, String productName, int productPrice, int discountPrice) {
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
