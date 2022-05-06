package ua.hw1.store.backstage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ua.hw1.store.Store;
import ua.hw1.store.db.DbUtils;


@Slf4j
public class StoreUtilsTest {
    StoreUtils storeUtils;
    DbUtils dbUtils;
    String productListABCD;
    String productListA;
    String productListC;
    String productListEmpty = "";

    @Before
    public void setUp() {
        this.dbUtils = new DbUtils();
        this.storeUtils = new StoreUtils();
        this.productListABCD = "ABcd";
        this.productListA = "AaAA";
        this.productListC = "cccccCc";
    }


    @Test
    public void doChequeGenerateFromStringTest() {
        Assertions.assertEquals(dbUtils.getListOfProductFromDb(),
                storeUtils.generateChequeByString(productListABCD));
        log.info("Test to generate cheque from a string");
    }

    @Test
    public void doTotalCostOfABCDProductListCalculateTest() { //cheque "ABCD"
        Assertions.assertEquals(7.35,
                storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListABCD)));
        log.info("Calculation test of: " + productListABCD);
    }

    @Test
    public void doTotalCostOfAProductListCalculateTest() { //cheque "AAAA"
        Assertions.assertEquals(4.35,
                storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListA)));
        log.info("Calculation test of: " + productListA);
    }

    @Test
    public void doTotalCostOfCProductListCalculateTest() { //cheque "CCCCCCC"
        Assertions.assertEquals(6.00,
                storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListC)));
        log.info("test of: " + productListC);
    }

    @Test
    public void doTotalCostOfEmptyBasketCalculateTest() { //test for empty basket
        Assertions.assertEquals(true, new Store().generateRandomCheque(0).isEmpty());
        Assertions.assertEquals(0.00,
                storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListEmpty)));
        log.info("calculationTestOfEmptyBasket");
    }

    @Test
    public void isCalculatedTotalCostForEmptyBasketTest() {
        Assert.assertFalse(!new Store().generateRandomCheque(0).isEmpty());
        log.info("booleanTestOfEmptyBasketIsFalse");
    }

    @Test
    public void isGeneratedRandomChequeTest() {
        Assert.assertFalse(new Store().generateRandomCheque(10).isEmpty());
        log.info("booleanTestOfRandomChequeIsFalse");
    }

}
