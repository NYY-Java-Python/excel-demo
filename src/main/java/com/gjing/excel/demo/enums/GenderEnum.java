package com.gjing.excel.demo.enums;


import cn.gjing.tools.excel.EnumConvert;

import javax.persistence.AttributeConverter;

/**
 * 性别
 *
 * @author Gjing
 **/
public enum GenderEnum {
    /**
     *
     */
    MAN(1, "男"),
    WOMAN(2, "女");
    private int type;
    private String desc;

    GenderEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * excel导入的时候转换成枚举用的
     *
     * @param s desc
     * @return GenderEnum
     */
    public static GenderEnum of(String s) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.desc.equals(s)) {
                return genderEnum;
            }
        }
        throw new NullPointerException("没找到你的枚举");
    }

    /**
     * JPA数据库查出来的时候转成枚举用的
     *
     * @param type 类型
     * @return GenderEnum
     */
    public static GenderEnum of(int type) {
        for (GenderEnum genderEnum : GenderEnum.values()) {
            if (genderEnum.type == type) {
                return genderEnum;
            }
        }
        throw new NullPointerException("没有你的枚举");
    }

    public static class GenderEnumDbConvert implements AttributeConverter<GenderEnum, Integer> {
        @Override
        public Integer convertToDatabaseColumn(GenderEnum genderEnum) {
            return genderEnum.type;
        }

        @Override
        public GenderEnum convertToEntityAttribute(Integer integer) {
            return of(integer);
        }
    }

    /**
     * 自定义枚举转换器
     */
    public static class MyExcelEnumConvert implements EnumConvert<GenderEnum, String> {

        @Override
        public GenderEnum toEntityAttribute(String s) {
            return of(s);
        }

        @Override
        public String toExcelAttribute(GenderEnum genderEnum) {
            return genderEnum.desc;
        }
    }
}
