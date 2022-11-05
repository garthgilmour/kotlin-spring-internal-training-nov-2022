package reactor.switching;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.String.format;

public class Program {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");

    private static String time() {
        return LocalTime.now().format(formatter);
    }

    private static Flux<String> buildBaseData() {
        return Flux.just("Alpha", "Bravo", "Charlie")
                .delayElements(Duration.ofSeconds(2));
    }

    private static Flux<String> buildTestData(String prefix) {
        Flux<String> testData = Flux.generate(
                () -> -1,
                (state, sink) -> {
                    int nextState = state + 1;
                    if (nextState == 21) {
                        sink.complete();
                    } else {
                        String template = "%s: %d at %s";
                        String message = format(template, prefix, nextState, time());
                        sink.next(message);
                    }
                    return nextState;
                });
        return testData.delayElements(Duration.ofMillis(250));
    }

    public static void main(String[] args) {
        buildBaseData()
                .switchMap(Program::buildTestData)
                .subscribe(
                        item -> System.out.println("\t" + item),
                        error -> System.out.println("Error: " + error.getMessage()),
                        () -> System.out.println("Stream complete")
                );

        System.out.println("Hit return to end");
        new Scanner(System.in).nextLine();
        System.out.println("Bye");
    }
}
