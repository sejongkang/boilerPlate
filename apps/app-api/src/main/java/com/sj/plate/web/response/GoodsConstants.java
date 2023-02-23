package com.sj.plate.web.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GoodsConstants {
    public static final ResponseEntity<Void> OK =
            ResponseEntity.ok().build();

    public static final ResponseEntity<Void> CREATED =
            ResponseEntity.status(HttpStatus.CREATED).build();
}
