package com.eazybank.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {//get the current auditor of the data
    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("ACCOUNTS_MS");
    }
}
