package io.bentkowski.store.entity;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ShopOrderSpecifications {

    public static Specification<ShopOrder> getProductOrdersBetweenDates(LocalDateTime beginDate, LocalDateTime endDate) {
        return((root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.between(root.get("created"),beginDate, endDate)
                );
    }
}
