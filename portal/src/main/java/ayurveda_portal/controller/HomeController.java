package ayurveda_portal.controller;

import ayurveda_portal.model.AyurvedaRecord;
import ayurveda_portal.service.AyurvedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AyurvedaService ayurvedaService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/search")
    public String search(

            @RequestParam String keyword,

            @RequestParam(required = false) String dosha,

            @RequestParam(required = false) String bodySystem,

            @RequestParam(required = false) List<String> symptoms,

            Model model) {

        List<AyurvedaRecord> results =
                ayurvedaService.search(
                        keyword,
                        dosha,
                        bodySystem,
                        symptoms
                );

        model.addAttribute("keyword", keyword);
        model.addAttribute("results", results);

        return "results";
    }
}