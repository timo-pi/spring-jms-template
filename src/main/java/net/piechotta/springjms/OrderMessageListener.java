package net.piechotta.springjms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {

    @JmsListener(destination = "Orders",
            containerFactory = "TopicListenerContainerFactory",
            subscription = "orders")
    public void onMessage(Order order) {
        System.out.println("Received: " + order);
    }

    /* To receive raw java.jms.Message objects use the following:
    @JmsListener(destination = "Orders",
            containerFactory = "TopicListenerContainerFactory",
            subscription = "orders")
    public void onMessage(javax.jms.Message order) {
        System.out.println(order);
    }
    */

}
