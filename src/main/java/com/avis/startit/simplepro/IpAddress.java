///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.avis.startit.simplepro;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetAddress;
//import java.net.InterfaceAddress;
//import java.net.MalformedURLException;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.URL;
//import java.util.Enumeration;
//
///**
// *
// * @author Akshay
// */
//public class IpAddress {
//
//    NetworkInterface ifcfg;
//    Enumeration<InetAddress> addresses;
//    String address;
//
//    public static void main(String[] args) throws IOException {
////        InetAddress address = getLocalAddress();
////        System.out.println("adress: " + address.toString());
//        getExternalAddress();
//    }
//
//    private static InetAddress getLocalAddress() {
//        try {
//            Enumeration<NetworkInterface> b = NetworkInterface.getNetworkInterfaces();
//            while (b.hasMoreElements()) {
//                for (InterfaceAddress f : b.nextElement().getInterfaceAddresses()) {
//                    if (f.getAddress().isSiteLocalAddress()) {
//                        return f.getAddress();
//                    }
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static void getExternalAddress() throws MalformedURLException, IOException {
//        URL whatismyip = new URL("http://checkip.amazonaws.com");
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                whatismyip.openStream()));
//
//        String ip = in.readLine(); //you get the IP as a String
//        System.out.println(ip);
//    }
//}
