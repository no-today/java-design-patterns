package state.domain.enumeration;

/**
 * 参考 Shopee 订单状态: https://open.shopee.com/documents?module=63&type=2&id=50
 */
public enum OrderStatus {

    /**
     * 未付款
     * 订单初始状态
     * 等待付款
     * <p>
     * 状态值为 [NULL] 时才可进入
     */
    UNPAID,

    /**
     * 卖家备货中
     * 买家付款后进入
     * 等待卖家备货至平台仓库
     * <p>
     * 前置状态必须为 [未付款]
     */
    READY_TO_SHIP,

    /**
     * 货物运输中: 该状态包含复杂的物流状态，在订单域，从运输到买家收货都处于该状态
     * 平台触发
     * 等待买家收货
     * <p>
     * 前置状态必须为 [备货中]
     */
    SHIPPED,

    /**
     * 确认收货
     * 买家触发
     * 订单流程结束
     * <p>
     * 前置状态必须为 [运输中]
     */
    TO_CONFIRM_RECEIVE,

    /**
     * 申请取消订单
     * 买家触发
     * 等待申请通过
     * <p>
     * 不得重复申请
     * 状态不得为 [已完成]，已完成可尝试走退货流程
     */
    IN_CANCEL,

    /**
     * 取消订单成功
     * 卖家或平台触发
     * 订单流程结束
     * <p>
     * 前置状态必须为 [申请取消订单]
     */
    CANCELLED,

    /**
     * 进入退货流程: 该状态包含退货流程
     * 买家触发
     * <p>
     * 前置状态必须为 [已完成]
     */
    TO_RETURN,

    /**
     * 订单流程结束
     * 自动流转
     */
    COMPLETED
}
