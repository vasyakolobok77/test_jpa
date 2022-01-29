package org.simple.testjpa;

import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class Test2_ReadInTz2 {
    static {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+10"));
    }

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void readIntTz2() {
        var item = itemRepository.findById(1).get();
        log.info("read in tz2: ts = {}, tstz = {}", item.getTs(), item.getTstz());
    }

}
