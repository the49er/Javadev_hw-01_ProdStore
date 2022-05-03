package ua.hw1.store;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ua.hw1.store.backstage.Product;
import ua.hw1.store.backstage.StoreUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */

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
