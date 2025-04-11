package com.lin.missyou.sample.kafka;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delay")
public class DelayTestController {
    private final DelayRouter router;

    public DelayTestController(DelayRouter router) {
        this.router = router;
    }

    @PostMapping
    public String send(
            @RequestParam String message,
            @RequestParam(required = false, defaultValue = "0") int delaySec) {

        router.sendWithDelay(message,  delaySec);
        return "Delayed to level: " + resolveLevelName(delaySec);
    }

    private String resolveLevelName(int seconds) {
        if (seconds <= 0) return "immediate";
        if (seconds <= 10) return "short(10s)";
        if (seconds <= 60) return "medium(1m)";
        return "long(1h)";
    }
}