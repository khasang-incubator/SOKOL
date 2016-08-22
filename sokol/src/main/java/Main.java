import io.khasang.sokol.beans.IPrinter;
import io.khasang.sokol.beans.impl.ConsolePrinter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.khasang.sokol.beans.impl")
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        IPrinter printer = context.getBean(ConsolePrinter.class);
        printer.print();
    }
}
