package factory;

/**
 * @description Mac 工厂，负责生产各种型号的 Mac
 * @author no-today
 * @date 2020/11/15 1:20 AM
 */
public class MacsFactory {

    public static Mac getMac(MacType type) {
        return type.getConstructor().get();
    }
}
