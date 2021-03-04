package basilisk.hooksCheckingService.messaging;

/**
 * @author Fakhr Shaheen
 */

import basilisk.hooksCheckingService.domain.docker.DockerImage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqMessager implements HookMessageSender{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${hooksCheckingService.rabbitmq.exchange}")
    private String exchange;

    @Value("${hooksCheckingService.rabbitmq.routingkey}")
    private String routingkey;

    public void send(DockerImage dockerImage) {
        rabbitTemplate.convertAndSend(exchange, routingkey, dockerImage);
        System.out.println("Send msg = " + dockerImage);

    }
}