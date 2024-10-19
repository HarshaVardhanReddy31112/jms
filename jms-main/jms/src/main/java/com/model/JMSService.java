package com.model;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

@Stateful
public class JMSService {

	@Resource(mappedName = "java:/jms/queue/DLQ")
	Queue jmsQ;

	@Resource(mappedName = "java:/ConnectionFactory")
	JMSContext context;

    public void sendMessage(String msg) {
        try {
            JMSProducer producer = context.createProducer();
            TextMessage txtMsg = context.createTextMessage(msg);
            producer.send(jmsQ, txtMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        String msg = "";
        try {
            JMSConsumer consumer = context.createConsumer(jmsQ);
            TextMessage txtMsg = (TextMessage) consumer.receive(5000); // Wait for 5 seconds
            if (txtMsg != null)
                msg = txtMsg.getBody(String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
