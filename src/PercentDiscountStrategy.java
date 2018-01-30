public class PercentDiscountStrategy
  implements DiscountStrategy {

  private float pct;

  public PercentDiscountStrategy(float pct) {
    this.pct = pct;
  }

  public float applyDiscount(Order order) {
    return order.getPrice() * (1 - this.pct/100);
  }
} 
