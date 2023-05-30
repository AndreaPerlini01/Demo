package com.example.demo;

import com.example.demo.entities.Auto;
import com.example.demo.service.AutoService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
public class AutoTest {

    private static String id;

    @Autowired
    AutoService service;

    static MysqlContainerBaseTest container;

    @BeforeAll
    static void setUp() throws SQLException {
        // connect to Docker
        container = new MysqlContainerBaseTest();

    }

    @AfterAll
    static void tearDown() throws SQLException {

        container.close();
    }

    @Test
    void add() {
        Auto auto = new Auto(null, "Test", "Test", 100);
        Auto autoAdded = service.doIt(auto);
        assertNotNull(autoAdded);
        assertNotNull(autoAdded.getId());
        assertEquals(auto.getName(), autoAdded.getModel());
        id = autoAdded.getId();
    }
}
