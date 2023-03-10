package com.sj.plate.goods.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="goods")
public class Goods extends BaseEntity{

    @Id
    @Column(name="goods_no")
    @Comment("상품 ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;

    @JsonIgnore
    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private List<GoodsItem> goodsItemList= new ArrayList<>();

    @Comment("등록 상품 상태")
    @Column(name="status_name")
    private String statusName;

    @Comment("노출 카테고리 코드")
    @Column(name="display_category_id")
    private Long displayCategoryId;

    @Comment("발주서에 사용되는 등록상품명")
    @Column(name="seller_goods_name", length = 200)
    private String sellerGoodsName;

    @Lob
    @Comment("상품설명")
    @Column(name="goods_cont")
    private String goodsDetails;

    @Comment("업체 아이디")
    @Column(name="com_id")
    private Long vendorId;

    @Comment("판매시작일시")
    @Column(name="sale_started_at", length = 20)
    private String saleStartedAt;

    @Comment("판매종료일시")
    @Column(name="sale_ended_at", length = 20)
    private String saleEndedAt;

    @Comment("상품 서비스에 노출될 상품명이며 브랜드, 제품명, 상품군 변경의해 자동변경될수 있습니다.")
    @Column(name="display_goods_name")
    private String displayGoodsName;

    @Comment("브랜드명은 한글/영문 표준이름")
    @Column(name="brand")
    private String brand;

    @Comment("배송방법 : SEQUENCIAL 일반(순차)배송, COLD_FRESH 신선냉동, MAKE_ORDER 주문제작, AGENT_BUY 구매대행, VENDOR_DIRECT 설치배송 또는 판매자 직접전달")
    @Column(name="delivery_method")
    private String deliveryMethod;

    @Comment("배송비종류: FREE 무료배송, NOT_FREE : 유료배송, CHARGE_RECEIVED: 착불배송, CONDITIONAL_FREE: 조건부 무료배송")
    @Column(name="delivery_company_code")
    private String deliveryCompanyCode;

    @Comment("기본배송비 : 유료배송 또는 조건부 무료배송시 편도 배송비 금액 입력")
    @Column(name="delivery_charge")
    private int deliveryCharge;

    @Comment("반품지 센터 ID")
    @Column(name="return_center_id")
    private Long returnCenterId;


}
