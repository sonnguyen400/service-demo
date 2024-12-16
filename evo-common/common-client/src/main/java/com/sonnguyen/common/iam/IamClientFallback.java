package com.sonnguyen.common.iam;

import com.sonnguyen.common.UserAuthority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class IamClientFallback implements FallbackFactory<IamClient> {
    @Override
    public IamClient create(Throwable cause) {
        return new FallbackWithFactory(cause);
    }

    @Slf4j
    static class FallbackWithFactory implements IamClient {
        private final Throwable cause;

        FallbackWithFactory(Throwable cause) {
            this.cause = cause;
        }

//        @Override
//        public UserAuthority getUserAuthority(UUID userId) {
//            if (cause instanceof ForwardInnerAlertException) {
//                return Response.fail((RuntimeException) cause);
//            }
//            return Response.fail(
//                    new ResponseException(ServiceUnavailableError.IAM_SERVICE_UNAVAILABLE_ERROR));
//        }

        @Override
        public UserAuthority getUserAuthority(Long userId) {
            return null;
        }

        @Override
        public UserAuthority getUserAuthority(String email) {
            return null;
        }

//        @Override
//        public Response<UserAuthority> getUserAuthority(String username) {
//            if (cause instanceof ForwardInnerAlertException) {
//                return Response.fail((RuntimeException) cause);
//            }
//            return Response.fail(
//                    new ResponseException(ServiceUnavailableError.IAM_SERVICE_UNAVAILABLE_ERROR));
//        }
    }
}
