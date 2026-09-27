package pe.cibertec.appgrupo8consumidor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pe.cibertec.appgrupo8consumidor.config.RabbitMQConfig;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class NumberConsumer {
    private final MergeSortService mergeSortService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(String cadenaNumeros) {
        System.out.println("Mensaje recibido: " + cadenaNumeros);
        Integer[] integerArray = Stream.of(cadenaNumeros.split(";"))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Integer[] resultado = mergeSortService.sort(integerArray);
        System.out.println("Lista ordenada: " + Arrays.toString(resultado));
    }
}