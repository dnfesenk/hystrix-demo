package tk.dfesenko.hystrixdemo.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "test", url = "http://localhost:8080/ui/")
public interface IFeignClient {

    @GetMapping("demo-client")
    ResponseEntity<String> getStringViaFeign();
}
