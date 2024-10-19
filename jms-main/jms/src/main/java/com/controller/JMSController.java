package com.controller;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import com.model.JMSService;

@ManagedBean(name = "jms", eager = true)
@ApplicationScoped
public class JMSController {
    private String msg;
    private String response;

    @Inject
    JMSService jms;

    public void callReceiveMessage() {
        msg = jms.receiveMessage();
        response = "Message Received @ " + new Date().toString();
    }

    public void callSendMessage() {
        jms.sendMessage(msg);
        response = "Message sent @ " + new Date().toString();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
