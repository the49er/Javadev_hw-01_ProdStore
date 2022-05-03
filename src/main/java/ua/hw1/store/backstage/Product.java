package ua.hw1.store.backstage;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    String productName;
    double regularPrice;
    int discountQuantity;
    double discountPrice;
}
