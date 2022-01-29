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
public class Test1_SaveInTz1 {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+5"));
    }

    @Autowired
    private ItemRepository itemRepository;

    @Order(0)
    @Test
    @Rollback(false)
    public void saveInTz1() {
        var inst = Instant.now();
        var item = new Item(1, inst, inst);
        itemRepository.saveAndFlush(item);

        log.info("save in tz1 = {}", inst);
    }

    @Order(1)
    @Test
    public void readIntTz1() {
        var item = itemRepository.findById(1).get();
        log.info("read in tz1: ts = {}, tstz = {}", item.getTs(), item.getTstz());
    }

}

