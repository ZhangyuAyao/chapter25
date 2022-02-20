package com.hspedu.jdbc.dao_.domain;

/**
 * @author: bytedance
 * @date: 2022/2/20
 * @description:
 */
public class Goods {
    private Integer id;
    private String goods_name;
    private Double price;

    public Goods() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public Double getPrice() {
        return price;
    }

    public Goods(Integer id, String goods_name, Double price) {
        this.id = id;
        this.goods_name = goods_name;
        this.price = price;
    }
}
