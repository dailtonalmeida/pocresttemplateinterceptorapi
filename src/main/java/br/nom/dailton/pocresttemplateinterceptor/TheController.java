package br.nom.dailton.pocresttemplateinterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/the/api/1.0")
public class TheController {
    private static final Logger log = LoggerFactory.getLogger(TheController.class);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> hello(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String auth) {
        log.atInfo().log("RECEIVED AUTH TOKEN: {}", auth);
        return Map.of("message", "Hello, I'm THE API!!!");
    }

}
