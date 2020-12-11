package org.geekbang.time.commonmistakes.clientdata.trustclientcalculation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RequestMapping("trustclientcalculation")
@RestController
public class TrustClientCalculationController {
    @PostMapping("/order")
    public void wrong(@RequestBody Order order) {
        this.createOrder(order);
    }


    /**
     * 客户端传送过来的数据都是不可靠的
     * 需要进行校验
     * 只需要校验需要校验的参数就可以了。
     * @param order
     */
    @PostMapping("/orderRight")
    public void right(@RequestBody Order order) {
        Item item = Db.getItem(order.getItemId());
        if (!order.getItemPrice().equals(item.getItemPrice())) {
            throw new RuntimeException("您选购的商品价格有变化，请重新下单");
        }
        order.setItemPrice(item.getItemPrice());
        BigDecimal totalPrice = item.getItemPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
        if (order.getItemTotalPrice().compareTo(totalPrice) != 0) {
            throw new RuntimeException("您选购的商品总价有变化，请重新下单");
        }
        order.setItemTotalPrice(totalPrice);
        createOrder(order);
    }

    @PostMapping("orderRight2")
    public Order right2(@RequestBody CreateOrderRequest createOrderRequest) {
        Item item = Db.getItem(createOrderRequest.getItemId());
        Order order = new Order();
        order.setItemPrice(item.getItemPrice());
        order.setItemTotalPrice(item.getItemPrice().multiply(BigDecimal.valueOf(order.getQuantity())));
        createOrder(order);
        return order;
    }

    private void createOrder(Order order) {

    }
}
