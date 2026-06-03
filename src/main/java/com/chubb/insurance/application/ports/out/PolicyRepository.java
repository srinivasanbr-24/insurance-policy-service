package com.chubb.insurance.application.ports.out;

import java.util.Optional;
import java.util.UUID;

public interface PolicyRepository {

    Optional<Object> findById(UUID id);

}