package com.flying.didi.Q7;

import org.springframework.transaction.annotation.Transactional;

/**
 * public interface TransactionDefinition {
 *     int PROPAGATION_REQUIRED = 0;
 *     int PROPAGATION_SUPPORTS = 1;
 *     int PROPAGATION_MANDATORY = 2;
 *     int PROPAGATION_REQUIRES_NEW = 3;
 *     int PROPAGATION_NOT_SUPPORTED = 4;
 *     int PROPAGATION_NEVER = 5;
 *     int PROPAGATION_NESTED = 6;
 * }
 *
 * Spring事务传播机制
 *
 *
 */
public class Test {

    @Transactional
    public void method(){

    }
}
