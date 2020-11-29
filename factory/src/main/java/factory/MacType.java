package factory;

import java.util.function.Supplier;

/**
 * @description Mac 型号库
 * @author no-today
 * @date 2020/11/15 1:22 AM
 */
public enum MacType {

    /**
     * Mac family
     */
    MAC_BOOK(MacBook::new),
    MAC_BOOK_AIR(MacBookAir::new),
    MAC_BOOK_PRO(MacBookPro::new);

    private final Supplier<Mac> constructor;

    MacType(Supplier<Mac> constructor) {
        this.constructor = constructor;
    }

    public Supplier<Mac> getConstructor() {
        return constructor;
    }
}
