package id.ac.ui.cs.advprog.fashionpediareport.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ReportController{
    @RequestMapping("")
    public String main(){
        return new String("Hello world");
    }
}