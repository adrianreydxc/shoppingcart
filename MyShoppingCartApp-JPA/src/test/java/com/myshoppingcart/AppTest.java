package com.myshoppingcart;

import com.myshoppingcart.config.SpringConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@EnableAutoConfiguration
class AppTest {

    @PersistenceContext
    EntityManager entityManager;

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;

    @Test
    public void load() {
        assertNotNull(entityManager);
        assertNotNull(entityManagerFactory);
        assertTrue(true);
    }


}