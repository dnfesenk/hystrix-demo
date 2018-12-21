package tk.dfesenko.hystrixdemo.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.dfesenko.hystrixdemo.clients.IFeignClient;

@RestController
@RequestMapping("ui")
public class DemoService {

    @Autowired
    private IFeignClient iFeignClient;

    @HystrixCommand(fallbackMethod = "getStringFallback")
    @GetMapping("demo")
    public ResponseEntity<String> getString() {
        return iFeignClient.getStringViaFeign();
    }

    @GetMapping("demo-client")
    public ResponseEntity<String> getStringViaFeign() throws InterruptedException {
        Thread.sleep(6000);
        return null;
    }

    @SuppressWarnings("unused")
    public ResponseEntity<String> getStringFallback() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAILED");
    }

}
