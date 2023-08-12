package narif.poc.projects.tacocloudmonolith.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "taco.orders")
public class OrderProps {

    private int pageSize = 20;
}
