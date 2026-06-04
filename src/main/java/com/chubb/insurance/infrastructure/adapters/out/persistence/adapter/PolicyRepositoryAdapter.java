package com.chubb.insurance.infrastructure.adapters.out.persistence.adapter;

import com.chubb.insurance.application.model.query.PolicySearchQuery;
import com.chubb.insurance.application.model.result.PolicySearchResult;
import com.chubb.insurance.application.model.result.PolicySummaryProjection;
import com.chubb.insurance.application.ports.out.PolicyRepository;
import com.chubb.insurance.domain.model.Policy;
import com.chubb.insurance.domain.model.PolicyId;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import com.chubb.insurance.infrastructure.adapters.out.persistence.mapper.PolicyPersistenceMapper;
import com.chubb.insurance.infrastructure.adapters.out.persistence.repository.PolicySpecification;
import com.chubb.insurance.infrastructure.adapters.out.persistence.repository.SpringDataPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PolicyRepositoryAdapter
        implements PolicyRepository {

    private final SpringDataPolicyRepository repository;

    private final PolicyPersistenceMapper mapper;

    @Override
    public Optional<Policy> findById(
            PolicyId id
    ) {

        return repository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public PolicySearchResult search(
            PolicySearchQuery query
    ) {

        Sort sort =
                Sort.by(
                        Sort.Direction.fromString(
                                query.sortDirection()
                        ),
                        query.sortBy()
                );

        Pageable pageable =
                PageRequest.of(
                        query.page(),
                        query.size(),
                        sort
                );

        Specification<PolicyJpaEntity> specification =
                Specification.where(
                                PolicySpecification.statusEquals(
                                        query.status()
                                )
                        )
                        .and(
                                PolicySpecification.lineOfBusinessEquals(
                                        query.lineOfBusiness() == null
                                                ? null
                                                : query.lineOfBusiness().name()
                                )
                        )
                        .and(
                                PolicySpecification.regionEquals(
                                        query.region()
                                )
                        )
                        .and(
                                PolicySpecification.effectiveDateFrom(
                                        query.effectiveDateFrom()
                                )
                        )
                        .and(
                                PolicySpecification.effectiveDateTo(
                                        query.effectiveDateTo()
                                )
                        )
                        .and(
                                PolicySpecification.searchText(
                                        query.search()
                                )
                        );

        Page<PolicyJpaEntity> page =
                repository.findAll(
                        specification,
                        pageable
                );

        return new PolicySearchResult(

                page.getContent()
                        .stream()
                        .map(mapper::toDomain)
                        .toList(),

                page.getNumber(),

                page.getSize(),

                page.getTotalElements(),

                page.getTotalPages()
        );
    }

    @Override
    public PolicySummaryProjection getSummary() {

        throw new UnsupportedOperationException(
                "Summary projection implementation will be added in Phase 10"
        );
    }

    @Override
    public void save(
            Policy policy
    ) {

        repository.save(
                mapper.toJpa(policy)
        );
    }
}