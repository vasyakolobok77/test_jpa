package org.simple.testjpa;

import java.time.Instant;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestItem {
    @Autowired
    private ItemRepository itemRepository;

    private static final TimeZone tz1 = TimeZone.getTimeZone("GMT+5");
    private static final TimeZone tz2 = TimeZone.getTimeZone("GMT+10");

    @Order(0)
    @Test
    @Rollback(false)
    public void saveInTz1() {
        TimeZone.setDefault(tz1);

        var inst = Instant.now();
        var item = new Item(1, inst, inst);
        itemRepository.saveAndFlush(item);

        log.info("save in tz1 = {}", inst);
    }

    @Order(1)
    @Test
    public void readIntTz1() {
        TimeZone.setDefault(tz1);

        var item = itemRepository.findById(1).get();
        log.info("read in tz1: ts = {}, tstz = {}", item.getTs(), item.getTstz());
    }

    @Order(2)
    @Test
    public void readIntTz2() {
        TimeZone.setDefault(tz2);

        var item = itemRepository.findById(1).get();
        log.info("read in tz2: ts = {}, tstz = {}", item.getTs(), item.getTstz());
    }

}

