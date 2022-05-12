package ua.hw1.store;

import lombok.extern.slf4j.Slf4j;
import ua.hw1.store.backstage.Product;
import ua.hw1.store.backstage.StoreUtils;
import ua.hw1.store.db.DbUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class Store {

    public static void main(String[] args) throws IOException {

        StoreUtils storeUtils = new StoreUtils();

        double randomReceipt = storeUtils.calculateTotalCost(generateRandomCheque(10));
        System.out.println("randomReceipt = " + randomReceipt);

        double clientReceipt = storeUtils.calculateTotalCost(storeUtils.generateCheque());
        System.out.println("clientReceipt = " + clientReceipt);

    }

    public static List<Product> generateRandomCheque(int count) {
        List<Product> assortment = new DbUtils().getListOfProductFromDb();
        List<Product> cheque = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cheque.add(assortment.get(new Random().nextInt(assortment.size())));
        }
        return cheque;
    }

}
