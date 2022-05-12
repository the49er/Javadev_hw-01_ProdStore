package ua.hw1.store.backstage;

import lombok.extern.slf4j.Slf4j;
import ua.hw1.store.db.DbUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class StoreUtils {

    DbUtils dbUtils = new DbUtils();

    public double calculateTotalCost(List<Product> cheque) {
        StringBuffer sb = new StringBuffer();
        for (Product product : cheque) {
            sb.append(product.getProductName());
        }
        String res = sb.toString();
        log.info("Total cost of cheque " + res + " has been counted");

        return cheque.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .map(n -> getProductTotalSum(n.getKey(), n.getValue()))
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    private double getProductTotalSum(Product product, long count) {
        double result;

        if (isDiscountQuantityNull(product)) {
            result = count / product.getDiscountQuantity() * product.getDiscountPrice() + count % product.getDiscountQuantity() * product.getRegularPrice();
        } else {
            result = count * product.getRegularPrice();
        }
        log.info("Total cost of product " + product.getProductName() + " is " + result);
        return result;
    }


    private boolean isDiscountQuantityNull (Product product) {
        boolean result = false;
        if (product.getDiscountQuantity() != 0){
            result = true;
        }
        return result;
    }


    public List<Product> generateCheque() throws IOException {
        List<Product> cheque = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] basket = new String[0];
        log.info("Create your own list of product out of A, B, C, D.\nInput the name of a product: ");
        try {
            basket = bufferedReader.readLine().split("");
        } catch (IOException e) {
            e.getMessage();
        }

        for (String product : basket) {
            switch (product.toUpperCase()) {
                case "A":
                    cheque.add(dbUtils.getListOfProductFromDb().get(0));
                    break;
                case "B":
                    cheque.add(dbUtils.getListOfProductFromDb().get(1));
                    break;
                case "C":
                    cheque.add(dbUtils.getListOfProductFromDb().get(2));
                    break;
                case "D":
                    cheque.add(dbUtils.getListOfProductFromDb().get(3));
                    break;
            }
        }
        bufferedReader.close();
        return cheque;
    }

    public List<Product> generateChequeByString(String str) {
        List<Product> cheque = new ArrayList<>();
        String[] basket = str.toUpperCase().split("");
        log.info("Create your own list of product (A, B, C, D)\nInput the name of a product");

        for (String product : basket) {
            switch (product.toUpperCase()) {
                case "A":
                    cheque.add(dbUtils.getListOfProductFromDb().get(0));
                    break;
                case "B":
                    cheque.add(dbUtils.getListOfProductFromDb().get(1));
                    break;
                case "C":
                    cheque.add(dbUtils.getListOfProductFromDb().get(2));
                    break;
                case "D":
                    cheque.add(dbUtils.getListOfProductFromDb().get(3));
                    break;
            }
        }
        return cheque;
    }
}
