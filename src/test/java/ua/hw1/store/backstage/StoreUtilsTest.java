package ua.hw1.store.backstage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

@Slf4j
public class StoreUtilsTest {
    StoreUtils storeUtils;
    String productListABCD;
    String productListAAAA;
    String productListCCCC;
    String productListEmpty;

    @Before
    public void setUp() {
        storeUtils = new StoreUtils();
        productListABCD = "ABcd";
        productListAAAA = "AaAA";
        productListCCCC = "cccccCc";
        productListEmpty = "";
    }

    @Test
    public void isGenerateChequeByString() { // test to generate basket
        Assertions.assertEquals(List.of(storeUtils.productA, storeUtils.productB, storeUtils.productC, storeUtils.productD), storeUtils.generateChequeByString(productListABCD));
    }

    @Test
    public void isCalculatedTotalCostForABCD() { //test for basket "ABCD"
        Assertions.assertEquals(7.35, storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListABCD)));
        log.info("testOfABCD");
    }

    @Test
    public void isCalculatedTotalCostFor4A() { //test for basket "AAAA"
        Assertions.assertEquals(4.35, storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListAAAA)));
        log.info("testOf4A");
    }

    @Test
    public void isCalculatedTotalCostFor7C() { //test for basket "CCCCCCC"
        Assertions.assertEquals(6.00, storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListCCCC)));
        log.info("testOf7C");
    }

    @Test
    public void isCalculatedTotalCostForEmptyBasket() { //test for empty basket
        Assertions.assertEquals(true, storeUtils.generateRandomCheque(0).isEmpty());
        Assertions.assertEquals(0.00, storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListEmpty)));
        log.info("testOfEmptyBasket");
    }

    @Test
    public void isGenerateRandomCheque() { //test of random generated cheque
        Assertions.assertEquals(false, storeUtils.generateRandomCheque(10).isEmpty());
        Assertions.assertEquals(10, storeUtils.generateRandomCheque(10).size());
        log.info("testOfRandomCheque");
    }

}
