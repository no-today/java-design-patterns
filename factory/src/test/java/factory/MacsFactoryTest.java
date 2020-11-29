package factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description
 * @author no-today
 * @date 2020/11/15 1:41 AM
 */
class MacsFactoryTest {

    @Test
    void test() {
        final Mac mac = MacsFactory.getMac(MacType.MAC_BOOK_PRO);
        assertTrue(mac instanceof MacBookPro);
    }
}