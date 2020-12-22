///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.avis.startit.simplepro;
//
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//
///**
// *
// * @author Akshay
// */
//public class Test {
//
//    private static int i = 0;
//
//    public static void main(String[] args) throws SocketException, UnknownHostException {
//        String body = "{\n"
//                + "  \"name\": \"akshay\",\n"
//                + "  \"fullName\": \"Akshay Kumar\",\n"
//                + "  \"domainId\": \"1585215258878\",\n"
//                + "  \"systemIp\": \"192.168.1.2\",\n"
//                + "  \"extension\": \"3003\",\n"
//                + "  \"extType\": \"INTERNAL\",\n"
//                + "  \"extRingType\": \"Y\",\n"
//                + "  \"beep\": \"Y\",\n"
//                + "  \"mode\": \"READY\",\n"
//                + "  \"pauseCode\": \"\",\n"
//                + "  \"lastStatus\": \"\",\n"
//                + "  \"campId\": \"0\",\n"
//                + "  \"skillName\": \"\",\n"
//                + "  \"serverId\": \"1585214930560\",\n"
//                + "  \"acceptRejectTimeout\": \"30\",\n"
//                + "  \"startEpoch\": \"1588324677\",\n"
//                + "  \"lastUpdatedOn\": \"2020-05-01 15:17:35.385\",\n"
//                + "  \"agentChannelId\": \"\",\n"
//                + "  \"listId\": \"1585215934113\",\n"
//                + "  \"processStatus\": \"\",\n"
//                + "  \"accountCode\": \"{{accountcode}}\",\n"
//                + "  \"uniqueId\": \"{{uniqueid}}\",\n"
//                + "  \"processUpdatedOn\": \"{{processupdatedon}}\",\n"
//                + "  \"interactionType\": \"{{interactiontype}}\",\n"
//                + "  \"qId\": \"1585215943959\",\n"
//                + "  \"qPriority\": \"{{qpriority}}\",\n"
//                + "  \"skillId\": \"{{skillid}}\",\n"
//                + "  \"agentId\": \"1585634081232\",\n"
//                + "  \"insertdate\": \"{{insertdate}}\",\n"
//                + "  \"transferredon\": \"{{transferredon}}\",\n"
//                + "  \"dispoId\": \"{{dispoid}}\",\n"
//                + "  \"startDate\": \"{{startdate}}\",\n"
//                + "  \"recordEntryDate\": \"{{recordentrydate}}\",\n"
//                + "  \"eventDate\": \"{{eventdate}}\",\n"
//                + "  \"interactionDuration\": \"{{interactionduration}}\",\n"
//                + "  \"queueDuration\": \"{{queueduration}}\",\n"
//                + "  \"dateHour\": \"{{datehour}}\",\n"
//                + "  \"week\": \"{{week}}\"\n"
//                + "}";
//
//        body = body.replaceAll("\\{\\{(.*?)\\}\\}", "");
////        body = body.replaceAll("\\{\\{([^}]*)\\}\\}", "");
////        System.out.println("body = " + body);
//
////        System.out.println(Runtime.getRuntime().availableProcessors());
////        System.out.println("ADMIN_REQUEST: {}" + Type.ADMIN_REQUEST.getValue());
////        System.out.println("TEST: {}" + Type.TEST.getValue());
////        m(i);
////        System.out.println("i = " + i);
//        try {
//            InetAddress ipAddr = InetAddress.getLocalHost();
//            System.out.println(ipAddr.getHostAddress());
//        } catch (UnknownHostException ex) {
//            ex.printStackTrace();
//        }
//        try (final DatagramSocket socket = new DatagramSocket()) {
//            socket.connect(InetAddress.getByName("8.8.8.8"), 80);
//            String ip = socket.getLocalAddress().getHostAddress();
//            System.out.println("ip = " + ip);
//        }
//
//    }
//
//    public static void m(int i) {
//        i += 2;
//    }
//}
