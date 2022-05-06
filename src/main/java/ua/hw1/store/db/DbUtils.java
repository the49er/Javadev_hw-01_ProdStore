package ua.hw1.store.db;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ua.hw1.store.backstage.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class DbUtils {

    public List<Product> getListOfProductFromDb() {
        String query = "select * from products";

        List<Product> productList = new ArrayList<>();
        Db db = new Db();
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getNString("product_name"));
                product.setRegularPrice(resultSet.getDouble("regular_price"));
                product.setDiscountQuantity(resultSet.getInt("discount_quantity"));
                product.setDiscountPrice(resultSet.getDouble("discount_price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
