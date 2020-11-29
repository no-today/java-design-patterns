package factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author no-today
 * @date 2020/11/15 1:18 AM
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        Mac macBook = MacsFactory.getMac(MacType.MAC_BOOK);
        Mac macBookAir = MacsFactory.getMac(MacType.MAC_BOOK_AIR);
        Mac macBookPro = MacsFactory.getMac(MacType.MAC_BOOK_PRO);

        log.info(macBook.getDescription());
        log.info(macBookAir.getDescription());
        log.info(macBookPro.getDescription());
    }
}
