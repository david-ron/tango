public class StoreCreditDiscountStrategy
  implements DiscountStrategy {

  private float amount;

  public StoreCreditDiscountStrategy(float amount) {
    this.amount = amount;
  }

  public float applyDiscount(Order order) {
    return order.getPrice() - this.amount;
  }
}
