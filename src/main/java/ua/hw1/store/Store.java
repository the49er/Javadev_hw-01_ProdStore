package ua.hw1.store;

import lombok.extern.slf4j.Slf4j;
import ua.hw1.store.backstage.StoreUtils;

@Slf4j
public class Store {

    public static void main(String[] args) {

        StoreUtils storeUtils = new StoreUtils();
        double randomReceipt = storeUtils.calculateTotalCost(storeUtils.generateRandomCheque(10));
        System.out.println("randomReceipt = " + randomReceipt);

        double clientReceipt = storeUtils.calculateTotalCost(storeUtils.generateCheque());
        System.out.println("clientReceipt = " + clientReceipt);
    }
}
