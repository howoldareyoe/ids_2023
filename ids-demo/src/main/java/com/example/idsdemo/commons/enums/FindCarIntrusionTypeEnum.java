package com.example.idsdemo.commons.enums;


public enum FindCarIntrusionTypeEnum {
    ByCarNumber(1,"car_number","根据车牌查找"),
    ByEmail(2,"user_email","根据邮箱查找"),
    ByAtackType(3,"intrusion_type","根据攻击类型查找");

    private Integer code;
    private String name;
    private String desc;

    FindCarIntrusionTypeEnum(Integer code, String name,String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static FindCarIntrusionTypeEnum getEnumByCode(Integer code){
        for (FindCarIntrusionTypeEnum item : FindCarIntrusionTypeEnum.values()){
            if (item.getCode().equals(code)){
                return item;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
