package factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description
 * @author no-today
 * @date 2020/11/15 1:38 AM
 */
class AppTest {

    @Test
    void test() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}