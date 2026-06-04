package com.chubb.insurance.infrastructure.adapters.out.cache.adapter;

import com.chubb.insurance.application.ports.out.PolicyCache;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import com.chubb.insurance.infrastructure.adapters.out.cache.mapper.PolicyCacheMapper;
import com.chubb.insurance.infrastructure.adapters.out.cache.model.CachedPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PolicyCacheAdapter
        implements PolicyCache {

    private static final String PREFIX =
            "policy:";

    private final RedisTemplate<String, Object> redisTemplate;

    private final PolicyCacheMapper mapper;

    @Override
    public Optional<Policy> findById(
            PolicyId id
    ) {

        Object value =
                redisTemplate.opsForValue()
                        .get(
                                PREFIX + id.value()
                        );

        if (value == null) {
            return Optional.empty();
        }

        CachedPolicy cached =
                (CachedPolicy) value;

        return Optional.of(
                mapper.toDomain(cached)
        );
    }

    @Override
    public void put(
            Policy policy
    ) {

        redisTemplate.opsForValue().set(
                PREFIX + policy.getId().value(),
                mapper.toCache(policy),
                Duration.ofMinutes(15)
        );
    }

    @Override
    public void evict(
            PolicyId id
    ) {

        redisTemplate.delete(
                PREFIX + id.value()
        );
    }
}