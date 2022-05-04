package ua.hw1.store.backstage;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Slf4j
public class StoreUtils {
    public Product productA = new Product("A", 1.35, 3, 3.00);
    public Product productB = new Product("B", 4.25, 0, 0.00);
    public Product productC = new Product("C", 1.00, 6, 5.00);
    public Product productD = new Product("D", 0.75, 0, 0.00);


    public double calculateTotalCost(List<Product> cheque) {

        log.info("Total cost has been counted");
        return cheque.stream()
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream()
                .map(n -> getProductTotalSum(n.getKey(), n.getValue()))
                .collect(Collectors.summingDouble(Double::doubleValue));
    }

    private double getProductTotalSum(Product product, long count) {
        double result = 0;

        //don't call the variables directly, it's an unsafe approach
        if (product.discountQuantity != 0) {
            //first of all, will nice to use () in those expressions to do them more readable
            //I also suggest extracting it to a separate private method with a meaningful name
            result = count / product.discountQuantity * product.discountPrice + count % product.discountQuantity * product.regularPrice;
        } else {
            result = count * product.regularPrice;
        }
        log.info("Total cost of product " + product.productName + " is " + result);
        return result;
    }

    //we don't have to explain what the method do in a comment. It should be clear enough from the method name

    //generates random client's basket
    public List<Product> generateRandomCheque(int count) {
        List<Product> assortment = List.of(productA, productB, productC, productD);
        List<Product> cheque = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cheque.add(assortment.get(new Random().nextInt(assortment.size())));
        }
        return cheque;
    }

    //here the same

    //generates client's basket
    public List<Product> generateCheque() {
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
                    cheque.add(productA);
                    break;
                case "B":
                    cheque.add(productB);
                    break;
                case "C":
                    cheque.add(productC);
                    break;
                case "D":
                    cheque.add(productD);
                    break;
            }
        }
        try {
            //is a good idea to close the stream in try block?
            //Do we have any way to be 100% sure that the stream will be closed?
            bufferedReader.close();
        } catch (IOException e) {
            e.getMessage();
        }
        return cheque;
    }

    //the method we use only in the test class.
    //We don't need to create any method for the test, we have to test our methods that contains our business logic
    public List<Product> generateChequeByString(String str) {
        List<Product> cheque = new ArrayList<>();
        String[] basket = str.toUpperCase().split("");
        log.info("Create your own list of product (A, B, C, D)\nInput the name of a product");

        for (String product : basket) {
            switch (product.toUpperCase()) {
                case "A":
                    cheque.add(productA);
                    break;
                case "B":
                    cheque.add(productB);
                    break;
                case "C":
                    cheque.add(productC);
                    break;
                case "D":
                    cheque.add(productD);
                    break;
            }
        }
        return cheque;
    }
}

// here is absolutely no reason keep the commented code here
// if code is unused it should be deleted in the final version of your application


//    double resA = productA.regularPrice * res.get(productA) * 100 / 100;
//        log.info(productA.productName + " | price: " + productA.regularPrice + " * quantity: " + res.get(productA) + " = sum: " + String.valueOf(resA));
//        double resB = productB.regularPrice * res.get(productB) * 100 / 100;
//        log.info(productB.productName + " | price: " + productB.regularPrice + " * quantity: " + res.get(productB) + " = sum: " + String.valueOf(resB));
//        double resC = productC.regularPrice * res.get(productC) * 100 / 100;
//        log.info(productC.productName + " | price: " + productC.regularPrice + "  * quantity: " + res.get(productC) + " = sum: " + String.valueOf(resC));
//        double resD = productD.regularPrice * res.get(productD) * 100 / 100;
//        log.info(productD.productName + " | price: " + productD.regularPrice + " * quantity: " + res.get(productD) + " = sum: " + String.valueOf(resD));
//        double sum = resA + resB + resC + resD;
//        log.info(String.valueOf(resA + " + " + resB + " + " + resC + " + " + resD + " = " + sum));


//    int quantityProductA = 0;
//        int quantityProductB = 0;
//        int quantityProductC = 0;
//        int quantityProductD = 0;
//
//        for (Product product : cheque) {
//            switch (product.productName) {
//                case "A":
//                    quantityProductA++;
//                    break;
//                case "B":
//                    quantityProductB++;
//                    break;
//                case "C":
//                    quantityProductC++;
//                    break;
//                case "D":
//                    quantityProductD++;
//                    break;
//            }
//        }

//        System.out.println("res.values() = " + res.values());
//        double resA = productA.regularPrice * res.get(productA) * 100 / 100;
//        log.info(productA.productName + " | price: " + productA.regularPrice + " * quantity: " + res.get(productA) + " = sum: " + String.valueOf(resA));
//        double resB = productB.regularPrice * res.get(productB) * 100 / 100;
//        log.info(productB.productName + " | price: " + productB.regularPrice + " * quantity: " + res.get(productB) + " = sum: " + String.valueOf(resB));
//        double resC = productC.regularPrice * res.get(productC) * 100 / 100;
//        log.info(productC.productName + " | price: " + productC.regularPrice + "  * quantity: " + res.get(productC) + " = sum: " + String.valueOf(resC));
//        double resD = productD.regularPrice * res.get(productD) * 100 / 100;
//        log.info(productD.productName + " | price: " + productD.regularPrice + " * quantity: " + res.get(productD) + " = sum: " + String.valueOf(resD));
//        double sum = resA + resB + resC + resD;
//        log.info(String.valueOf(resA + " + " + resB + " + " + resC + " + " + resD + " = " + sum));
//
//
//        System.out.println("res = " + res);
//
////            for (Map.Entry<Product, Long> item: res.entrySet()) {
////                totalCost += getProductTotalSum(item.getKey(), item.getValue());
////            }
//
//        totalCost = res.entrySet().stream()
//                .map(n -> getProductTotalSum(n.getKey(), n.getValue()))
//                .collect(Collectors.summingDouble(Double::doubleValue));


