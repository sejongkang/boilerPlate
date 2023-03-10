package com.sj.plate.web;

import com.sj.plate.web.Service.GoodsAppService;
import com.sj.plate.web.request.GoodsRequest;
import com.sj.plate.web.response.GoodsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@Api(tags = "상품")
@RequiredArgsConstructor
public class GoodsController {
    private final GoodsAppService goodsAppService;

    @GetMapping("/test")
    public String getTest(){
        return "goods api server";
    }


    @ApiOperation(value = "상품조회", notes = "상품조회", tags = "상품")
    @ApiResponses({
            @ApiResponse(code=200, message="goodsNo list로 전달받은 결과물을 리턴합니다.")
    })
    @GetMapping("/goods")
    public List<GoodsResponse> getGoodsByNo(@RequestParam(required=true) List<Long> goodsNos){
        return goodsAppService.getAllByGoods(goodsNos);
    }

    @ApiOperation(value = "상품등록", notes = "상품등록", tags = "상품")
    @ApiResponses({
            @ApiResponse(code=200, message="상품등록시 요청한 데이터를 리턴합니다.")
    })
    @PostMapping("/goods")
    public List<GoodsResponse> createGoods(@RequestBody List<GoodsRequest> goodsRequests){
         return goodsAppService.createGoods(goodsRequests);
    }

}