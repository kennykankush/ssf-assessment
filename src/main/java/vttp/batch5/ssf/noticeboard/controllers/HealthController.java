package vttp.batch5.ssf.noticeboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/status")
public class HealthController {

    @Autowired
    @Qualifier("StringTemplate")
    private RedisTemplate<String,Object> template;

    @GetMapping
    public ResponseEntity<String> healthChecker() { //THE REDIS-CLI COMMAND FOR randomkey() is RANDOMKEY

        try {

        if (!template.randomKey().isEmpty()){
            return ResponseEntity
                    .status(200)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{}");
        } 
        
        }   catch (Exception e){

        return ResponseEntity
                .status(503)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}");

        }

        return ResponseEntity
                .status(400)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{}");

   }


        
    }
    




    

    
