package com.sj.plate.goods.service;

import com.sj.plate.goods.entity.Goods;
import com.sj.plate.goods.repository.GoodsRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;

    public Optional<Goods> getByGoodsNo(Long goodsId){
        return goodsRepository.findByGoodsId(goodsId);
    }

    @Transactional(readOnly = true)
    public List<Goods> getAllByGoodsId(List<Long> goodsIds){
        return goodsRepository.findAllByGoodsIdIn(goodsIds);
    }

    public List<Goods> getAllByVendorId(List<String> vendorIds){
        return goodsRepository.getAllByVendorIdIn(vendorIds);
    }


    @Transactional
    public Goods save(Goods goods){
        return goodsRepository.saveAndFlush(goods);
    }
}
