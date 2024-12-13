package vttp.batch5.ssf.noticeboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.services.NoticeService;
import vttp.batch5.ssf.noticeboard.utils.JsonManagement;




@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeServ;   

    @GetMapping 
    public String showHome(Model model) {

        model.addAttribute("notice", new Notice());
    
        return "notice";
    }

    @PostMapping("/notice")
    public String createNotice(@ModelAttribute @Valid Notice notice, BindingResult formResults, Model model){

        System.out.println("Entering controller");

        if (formResults.hasErrors()){
            return "notice";
        }

        JsonObject json = JsonManagement.stringToJsonBuild(notice);

        // System.out.println("json Constructed ---> " + json.toString());

        String[] fetchResult = noticeServ.postToNoticeServer(notice, json);
        String exceptionErrorDetector = fetchResult[0];
        
        if (exceptionErrorDetector.equals("false")){

            model.addAttribute("errorMessage", fetchResult[1]);
            return "errormessage";
        }

        String id = fetchResult[1];

        model.addAttribute("id", "The notice posting id is " + id);

        System.out.println("Exiting controller");

        return "success";
        
        
    }
    





}
