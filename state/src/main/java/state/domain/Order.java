package state.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import state.domain.enumeration.OrderStatus;

import java.time.Instant;
import java.util.List;

/**
 * 精简订单模型
 */
@Data
@Accessors(chain = true, fluent = true)
public class Order {

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 状态
     */
    private OrderStatus status;

    /**
     * 创建时间
     */
    private Instant createdTime;

    /**
     * 修改时间
     */
    private Instant modifiedTime;

    /**
     * 支付时间
     */
    private Instant payTime;

    /**
     * 总付款金额
     */
    private Float totalAmount;

    /**
     * 购买的商品
     */
    private List<Item> items;

    /**
     * 订单项模型，生命周期跟随订单
     */
    @Data
    @Accessors(chain = true, fluent = true)
    public static class Item {

        /**
         * 商品ID
         */
        private String itemId;

        /**
         * 商品SKU ID
         */
        private String itemSkuId;

        /**
         * 数量
         */
        private Integer quantity;

        /**
         * 单价
         */
        private Float price;
    }
}
