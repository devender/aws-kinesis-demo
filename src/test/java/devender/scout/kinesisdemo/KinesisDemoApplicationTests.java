package devender.scout.kinesisdemo;


import org.junit.Assert;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@SpringBootTest(classes = KinesisDemoApplication.class)
class KinesisDemoApplicationTests {

    @Autowired
    private Processor processor;

    @Test
    void contextLoads() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            Map<String, Long> longMap = new HashMap<>(1);
            longMap.put("Time", System.currentTimeMillis());
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            processor.output().send(MessageBuilder.withPayload(longMap).build());
        }

        Assert.assertTrue(true);
    }

}
