package com.sonnguyen.common.iam;

import com.sonnguyen.common.config.OpenFeignTokenInterceptor;
import com.sonnguyen.common.UserAuthority;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(url = "${app.iam.internal-url}", name = "iam", configuration = OpenFeignTokenInterceptor.class)
public interface IamClient {
    @GetMapping("/api/v1/account/{userId}/authorities")
    UserAuthority getUserAuthority(@PathVariable Long userId);

    @GetMapping("/api/v1/account/{email}/authorities-by-email")
    UserAuthority getUserAuthority(@PathVariable String email);
}
