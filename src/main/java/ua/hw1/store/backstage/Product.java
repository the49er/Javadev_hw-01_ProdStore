package ua.hw1.store.backstage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Product {
    private int id;
    private String productName;
    private double regularPrice;
    private int discountQuantity;
    private double discountPrice;

}
