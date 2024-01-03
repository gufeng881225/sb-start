package sb.deploy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "liquibase")
@Data
public class DeployLiquibaseConfigurationProperties {
    private Map dataSource;
}