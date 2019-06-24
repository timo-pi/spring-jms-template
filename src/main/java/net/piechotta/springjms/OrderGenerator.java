package net.piechotta.springjms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderGenerator {

    private JmsTemplate jmsTemplate;
    private static final Log LOG = LogFactory.getLog(OrderGenerator.class);

    public OrderGenerator(@Qualifier("topicJmsTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @SuppressWarnings("Duplicates")
    @Scheduled(fixedDelay = 4000)
    public void generateOrder() {
        Order order = new Order();
        order.setOrderId(1L);
        order.setAmount(BigDecimal.TEN);
        order.setReference("Reference1");
        LOG.info("Sending order " + order);
        jmsTemplate.convertAndSend("Orders", order);
    }
}
