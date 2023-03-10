package com.sj.plate.web.request;

import com.sj.plate.goods.entity.Goods;
import com.sj.plate.goods.entity.GoodsItem;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRequest {

    private Long goodsId;               //상품ID

    private Long vendorId;              //입점 (파트너) ID

    private String sellerGoodsName;     //발주서 상품명

    private String displayGoodsName;    //노출될 상품명

    private String brand;               //브랜드명

    private String deliveryMethod;      //배송방법 : SEQUENCIAL 일반(순차)배송, COLD_FRESH 신선냉동, MAKE_ORDER 주문제작, AGENT_BUY 구매대행, VENDOR_DIRECT 설치배송 또는 판매자 직접전달

    private String deliveryCompanyCode; //배송비종류: FREE 무료배송, NOT_FREE : 유료배송, CHARGE_RECEIVED: 착불배송, CONDITIONAL_FREE: 조건부 무료배송

    private int deliveryCharge;         //기본배송비

    private Long returnCenterId;        //반품지 센터 ID

    private String goodsDetails;        //상품 정보

    private String saleStartedAt;       //판매 시작일

    private String saleEndedAt;         //판매 종료일

    @Builder.Default
    private List<GoodsItem> goodsItemList = new ArrayList<>();


    public Goods toEntity(){
        return Goods.builder()
                .vendorId(vendorId)
                .sellerGoodsName(sellerGoodsName)
                .displayGoodsName(displayGoodsName)
                .brand(brand)
                .deliveryMethod(deliveryMethod)
                .deliveryCompanyCode(deliveryCompanyCode)
                .deliveryCharge(deliveryCharge)
                .returnCenterId(returnCenterId)
                .goodsDetails(goodsDetails)
                .saleStartedAt(saleStartedAt)
                .saleEndedAt(saleEndedAt)
                .goodsItemList(goodsItemList)
                .build();
    }

}
