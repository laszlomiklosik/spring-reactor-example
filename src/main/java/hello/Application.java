package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;

import java.util.concurrent.CountDownLatch;

import static reactor.event.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

    private static final int NUMBER_OF_CUSTOM1_EVENTS = 5;
    private static final int NUMBER_OF_CUSTOM2_EVENTS = 6;

    @Bean
    Environment env() {
        return new Environment();
    }
    
    @Bean
    Reactor createReactor(Environment env) {
        return Reactors.reactor()
                .env(env)
                .dispatcher(Environment.RING_BUFFER)
                .get();
    }
    
    @Autowired
    private Reactor reactor;
    
    @Autowired
    private CustomEvent1Receiver customEvent1Receiver;

    @Autowired
    private CustomEvent2Receiver customEvent2Receiver;
    
    @Autowired
    private Publisher publisher;
    
    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(2); // TODO separate latches??
    }
    
    @Override
    public void run(String... args) throws Exception {        

        reactor.on($("custom1Events"), customEvent1Receiver);
        publisher.publishCustom1Events(NUMBER_OF_CUSTOM1_EVENTS);

        reactor.on($("custom2Events"), customEvent2Receiver);
        publisher.publishCustom2Events(NUMBER_OF_CUSTOM2_EVENTS);
    }
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }

}
