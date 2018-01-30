import java.util.Random;

public class RandomDiscountStrategy
  implements DiscountStrategy {

  private Random rng;

  public RandomDiscountStrategy() {
    this.rng = new Random();
  }

  public float applyDiscount(Order order) {
    return order.getPrice() * rng.nextFloat();
  }
}
