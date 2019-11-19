package devender.scout.kinesisdemo;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class KinesisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KinesisDemoApplication.class, args);
    }

    @Bean
    AWSCredentialsProvider awsCredentialsProvider(
            @Value("${cloud.aws.credentials.access-key}") String awsAccessKey,
            @Value("${cloud.aws.credentials.secret-key}") String awsSecret) {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecret));
    }

    @Autowired
    private Processor processor;

    @StreamListener(Processor.INPUT)
    public void consume(Map<String, Object> message) {
        log.info("Received Message: {}", message);
    }
}
