package br.nom.dailton.pocresttemplateinterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/the/api/1.0")
public class TheController {
    private static final Logger log = LoggerFactory.getLogger(TheController.class);

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> hello(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String auth,
                                     @RequestHeader(value = "X-EXPIRES-AT", required = false) Instant expiresAt) {
        log.atInfo().log("RECEIVED AUTH TOKEN: {} EXPIRES-AT:{}", auth, expiresAt);

        if (expiresAt == null || Instant.now(Clock.systemUTC()).isAfter(expiresAt)) {
            log.atInfo().log("API WILL PRODUCE INFORMATION OF UNAUTHORIZED");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.atInfo().log("API WILL PRODUCE INFORMATION OF OK");
        return ResponseEntity.ok(Map.of("message", "Hello, I'm THE API!!!"));
    }

}
