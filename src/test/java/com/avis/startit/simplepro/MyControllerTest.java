/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avis.startit.simplepro;

import com.avis.startit.simplepro.controller.MyController;
import lombok.extern.log4j.Log4j2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Akshay Kumar
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Log4j2
public class MyControllerTest {

    @Autowired
    private MyController myController;

//    @Test
//    public void getsAllRides() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/ride")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
    @Test
    public void testTimeOut() throws Exception {
        log.warn("**********************************");
        String response = "SUCCESS";
        assertEquals(response, myController.testTimeout(5).getBody());
    }
}
