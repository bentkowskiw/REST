package io.bentkowski.store.entity;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ShopOrderSpecifications {

    private static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static Specification<ShopOrder> getProductOrdersBetweenDates(LocalDateTime beginDate, LocalDateTime endDate) {
        Date begin, end;
        begin = convertToDateViaInstant(beginDate);
        end = convertToDateViaInstant(endDate);
        return((root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.between(root.get("created"),begin, end)
                );
    }
}
