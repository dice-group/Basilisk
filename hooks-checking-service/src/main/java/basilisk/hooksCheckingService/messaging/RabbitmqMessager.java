package basilisk.hooksCheckingService.messaging;

/**
 * @author Fakhr Shaheen
 */

import basilisk.hooksCheckingService.domain.docker.DockerImage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class RabbitmqMessager implements HookMessageSender{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${hooksCheckingService.rabbitmq.exchange}")
    private String exchange;

    @Value("${hooksCheckingService.rabbitmq.routingkey}")
    private String routingkey;

    public void send(DockerImage dockerImage) {
        rabbitTemplate.convertAndSend(exchange, routingkey, dockerImage.toString());
        System.out.println("Send msg = " + dockerImage);

    }
}