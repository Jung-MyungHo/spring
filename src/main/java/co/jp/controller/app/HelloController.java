package co.jp.controller.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
  
  @RequestMapping(value = "/hello")
  public String hello_view(Model model, @RequestParam("myName") String name) {
    model.addAttribute("myName", name);
    return "hello";
  }
}
