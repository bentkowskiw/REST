package io.bentkowski.store.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {


}
