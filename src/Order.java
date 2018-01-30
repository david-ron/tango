public class Order {
  private String name;
  private float price;

  public Order(String name, float price) {
    this.name = name;
    this.price = price;
  }

  public String getName() {
    return this.name;
  }

  public float getPrice() {
    return this.price;
  }
}
