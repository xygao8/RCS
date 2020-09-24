package com.iflytek.rcs.controller;//package com.message.controller;
    //
    //import org.springframework.http.HttpStatus;
    //import org.springframework.web.bind.annotation.*;
    //
    //import javax.servlet.http.HttpServletRequest;
    //import javax.servlet.http.HttpServletResponse;
    //
    ///**
    // * @author zzl
    // * @date 2020/7/6 15:50
    // * @description
    // */
    //@RestController
    //public class MessageReceiver {
    //
    //    //47.115.126.119:8089/InboundMessageNotification/sip:12520015000002@botplatform.rcs.chinamobile.com
    //
    //    @PostMapping("/InboundMessageNotification/{userId}")
    //    public String receiveMsg(@PathVariable String userId, @RequestBody(required = false) String body, HttpServletRequest request, HttpServletResponse response) {
    //        System.out.println("chatbot" + userId);
    //
    //        if (body != null && body.length() > 0) {
    //            response.setStatus(204);
    //            System.out.println(body);
    //        }
    //
    //        String tid = request.getHeader("tid");
    //        String authstatus = request.getHeader("Authstatus");
    //
    //        if (tid != null && tid.length() > 0) {
    //            response.setStatus(200);
    //            System.out.println("媒体文件tid"+tid);
    //            System.out.println(String.format("媒体审核结果%s", "0".equals(authstatus) ? "通过" : "拒绝"));
    //        }
    //
    //        return null;
    //    }
    //
    //    /**
    //     * Chatbot接收A2P消息的状态报告通知
    //     *
    //     * @param userId
    //     * @param body
    //     * @return
    //     */
    //    @PostMapping("/DeliveryInfoNotification/{userId}")
    //    @ResponseStatus(HttpStatus.NO_CONTENT)
    //    public String receiveStatus(@PathVariable String userId, @RequestBody String body) {
    //        System.out.println("chatbot" + userId);
    //        System.out.println(body);
    //        return null;
    //    }
    //
    //}
