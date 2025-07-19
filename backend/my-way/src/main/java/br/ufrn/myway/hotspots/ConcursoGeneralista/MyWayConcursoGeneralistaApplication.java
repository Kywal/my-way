package br.ufrn.myway.hotspots.ConcursoGeneralista;

import br.ufrn.myway.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "br.ufrn.myway.coldspots",
                "br.ufrn.myway.hotspots.ConcursoGeneralista"
        },
        scanBasePackageClasses = {
                DataInitializer.class
        }
)
@EnableJpaRepositories(
        basePackages = {
                "br.ufrn.myway.coldspots",
                "br.ufrn.myway.hotspots.ConcursoGeneralista",
                "br.ufrn.myway.hotspots.Goal"
        }
)
@EntityScan(basePackages = {
        "br.ufrn.myway.coldspots",
        "br.ufrn.myway.hotposts.Goal"
})
public class MyWayConcursoGeneralistaApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyWayConcursoGeneralistaApplication.class);
        application.run(args);
    }
}
