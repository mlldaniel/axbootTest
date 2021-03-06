package smartfactory;

import com.chequer.axboot.core.AXBootCoreConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:axboot-common.properties", "classpath:axboot-${spring.profiles.active:production}.properties"})
@Import(AXBootCoreConfiguration.class)
public class AXBootApplication {
}
