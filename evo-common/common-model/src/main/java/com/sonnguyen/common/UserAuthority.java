package com.sonnguyen.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthority {
    private Long id;
    private String email;
    private Boolean isVerified;
    private Boolean isLocked;
    private Boolean isRoot;
    private List<String> grantedAuthority;
}
