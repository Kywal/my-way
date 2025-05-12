package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PomodoroTesteController {

    @GetMapping("/")
    public String pomodoroteste() {
        return "pomodoroteste"; // carrega o arquivo templates/index.html
    }
}
