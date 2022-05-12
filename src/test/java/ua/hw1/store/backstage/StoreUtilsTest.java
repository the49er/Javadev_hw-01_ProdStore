package ua.hw1.store.backstage;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import ua.hw1.store.Store;
import ua.hw1.store.db.DbUtils;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Slf4j
public class StoreUtilsTest {
    StoreUtils storeUtils;
    // Here we have mock the DbUtils as internal dependency. I'll add a small example
    @Mock
    DbUtils mockDbutils;
    @Mock StoreUtils mockStoreUtils;
    String productListABCD;
    String productListA;
    String productListC;
    String productListEmpty = "";

    @Before
    public void setUp() {
        this.mockDbutils = mock(DbUtils.class);
        this.storeUtils = new StoreUtils();
        this.mockStoreUtils = mock(StoreUtils.class);
        this.productListABCD = "ABcd";
        this.productListA = "AaAA";
        this.productListC = "cccccCc";
    }

    @Test
    public void doChequeGenerateFromStringTest() {
        when(mockDbutils.getListOfProductFromDb()).thenReturn(storeUtils.generateChequeByString(productListABCD));
        Assertions.assertEquals(mockDbutils.getListOfProductFromDb(),
                storeUtils.generateChequeByString(productListABCD));
        log.info("Test to generate cheque from a string");
        verify(mockDbutils, times(1)).getListOfProductFromDb();
    }


    @Test
    public void doTotalCostOfABCDProductListCalculateTest() { //cheque "ABCD"
        when(mockStoreUtils.calculateTotalCost(mockStoreUtils.generateChequeByString(productListABCD))).thenReturn(7.35);
        Assertions.assertEquals(7.35,
                mockStoreUtils.calculateTotalCost(mockStoreUtils.generateChequeByString(productListABCD)));

        verify(mockStoreUtils, times(2)).calculateTotalCost(mockStoreUtils.generateChequeByString(productListABCD));
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
    public void doTotalCostOfEmptyBasketCalculateTest() { //test of empty basket
        Assertions.assertEquals(true, new Store().generateRandomCheque(0).isEmpty());
        Assertions.assertEquals(0.00,
                storeUtils.calculateTotalCost(storeUtils.generateChequeByString(productListEmpty)));
        log.info("calculationTestOfEmptyBasket");
    }

    @Test
    public void isCalculatedTotalCostForEmptyBasketTest() {
        Assert.assertFalse(!Store.generateRandomCheque(0).isEmpty());
        log.info("booleanTestOfEmptyBasketIsFalse");
    }

    @Test
    public void isGeneratedRandomChequeTest() {
        Assert.assertFalse(Store.generateRandomCheque(10).isEmpty());
        log.info("booleanTestOfRandomChequeIsFalse");
    }

}
