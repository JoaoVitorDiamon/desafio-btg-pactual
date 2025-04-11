package com.techBTG.testBTG.listener;


import com.techBTG.testBTG.Service.OrderService;
import com.techBTG.testBTG.listener.DTO.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.techBTG.testBTG.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {

        orderService.save(message.getPayload());

    }
}
