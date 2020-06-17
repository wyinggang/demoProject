package com.juc;

public enum CountryEnum {

    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"),
    FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    private Integer retCode;
    private String retMsg;

    private CountryEnum(Integer retCode, String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    //枚举值已经设定好了,不需要有set方法
    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum element : values) {
            if (index == element.getRetCode()) {
                return element;
            }
        }
        return null;
    }
}