package com.xupt.o2o.enums;

public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"),
    PATH(2, "通过认证"), INNER_ERROR(-1001, "内部系统错误"),NULL_SHOPID(-1002,"ShopId为空");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    
    /*
     * @Author yunchenhui
     * @Description //TODO 依据传入的state返回响应的enum值
     * @Date 21:26 2020/2/6
     * @Param 
     * @return 
     **/
    public static ShopStateEnum stateOf(int state){
        for(ShopStateEnum stateEnum:values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
