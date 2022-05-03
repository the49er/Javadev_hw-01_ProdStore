package ua.hw1.store.backstage;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ua.hw1.store.Store;

import java.util.List;


public class StoreUtilsTest {
    StoreUtils storeUtils = new StoreUtils();

    @Test
    public void isCalculatedTotalCostForABCD(){
        Assertions.assertEquals(7.35, storeUtils.calculateTotalCost(storeUtils.generateCheque()));
    }

}
