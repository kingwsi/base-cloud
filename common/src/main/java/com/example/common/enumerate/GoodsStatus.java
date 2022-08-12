package com.example.common.enumerate;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum GoodsStatus implements IEnum<String> {
    ON_SELL,
    EDIT,
    UNDER_REVIEW
    ;

    @Override
    public String getValue() {
        return name();
    }
}
