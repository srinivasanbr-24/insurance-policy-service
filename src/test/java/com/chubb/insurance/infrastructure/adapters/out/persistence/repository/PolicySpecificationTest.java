package com.chubb.insurance.infrastructure.adapters.out.persistence.repository;

import com.chubb.insurance.domain.model.LineOfBusiness;
import com.chubb.insurance.domain.model.PolicyStatus;
import com.chubb.insurance.infrastructure.adapters.out.persistence.entity.PolicyJpaEntity;
import com.chubb.insurance.infrastructure.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = {
        "spring.flyway.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Import(JpaConfig.class)
class PolicySpecificationTest {


    @Autowired
    private SpringDataPolicyRepository repository;


    @Test
    void shouldFilterByStatus() {


        PolicyJpaEntity entity = new PolicyJpaEntity();

        entity.setId(UUID.randomUUID());
        entity.setPolicyNumber("POL-1001");
        entity.setPolicyholderName("John Doe");
        entity.setUnderwriter("CHUBB");
        entity.setStatus(PolicyStatus.ACTIVE);
        entity.setLineOfBusiness(LineOfBusiness.PROPERTY);
        entity.setRegion("APAC");
        entity.setCurrencyCode("USD");
        entity.setPremiumAmount(BigDecimal.valueOf(1000));
        entity.setEffectiveDate(LocalDate.now());
        entity.setExpirationDate(LocalDate.now().plusYears(1));

        repository.save(entity);

        List<PolicyJpaEntity> result =
                repository.findAll(
                        PolicySpecification.statusEquals(
                                com.chubb.insurance.domain.model.PolicyStatus.ACTIVE
                        )
                );

        assertThat(result)
                .hasSize(1);
    }
}