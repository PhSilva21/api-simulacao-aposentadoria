package com.bandeira.sistema_aposentadoria.infra.feign;

import com.bandeira.sistema_aposentadoria.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "api-users", url = "http://localhost:8080/api/v1/users")
public interface UserFeign {


    @GetMapping(params =  "cpf")
    User findByCpf(@RequestParam("cpf") String cpf);
}
