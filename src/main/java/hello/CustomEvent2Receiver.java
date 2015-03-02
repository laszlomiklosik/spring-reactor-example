package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.event.Event;
import reactor.function.Consumer;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Service
class CustomEvent2Receiver implements Consumer<Event<CustomEvent2>> {

    @Autowired
    private CountDownLatch latch;

    public void accept(Event<CustomEvent2> ev) {
        Quote quote = new Quote(UUID.randomUUID().getLeastSignificantBits(), "custom event 2 quote");
        System.out.println("Quote " + ev.getData() + ": " + quote);
        latch.countDown();
    }
}