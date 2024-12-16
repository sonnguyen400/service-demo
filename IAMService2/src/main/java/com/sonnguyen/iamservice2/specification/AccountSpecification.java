package com.sonnguyen.iamservice2.specification;

import com.sonnguyen.common.specification.AbstractSpecification;
import com.sonnguyen.common.specification.DynamicSearch;
import com.sonnguyen.iamservice2.model.Account;

public class AccountSpecification extends AbstractSpecification<Account> {
    public AccountSpecification(DynamicSearch criteria) {
        super(criteria);
    }
}
