package com.sj.plate.web.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sj.plate.goods.entity.GoodsItem;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsResponse {

    private Long goodsId;

    private String statusName;

    private Long displayCategoryId;

    private String sellerGoodsName;

    private String goodsDetails;

    private Long vendorId;

    private String saleStartedAt;

    private String saleEndedAt;

    private String displayGoodsName;

    private String brand;

    private String deliveryMethod;

    private String deliveryCompanyCode;

    private int deliveryCharge;

    private Long returnCenterId;

    private List<GoodsItem> goodsItems;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDm;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate modDm;


}
