package com.sj.plate.goods.repository;

import com.sj.plate.goods.entity.Goods;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    Optional<Goods> findByGoodsId(Long goodsId);

    List<Goods> findAllByGoodsIdIn(List<Long> goodsId);

    List<Goods> getAllByVendorIdIn(List<String> vendorId);

}
