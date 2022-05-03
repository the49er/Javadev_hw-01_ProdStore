package ua.hw1.store.backstage;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    public String productName;
    public double regularPrice;
    public int discountQuantity;
    public double discountPrice;
}
