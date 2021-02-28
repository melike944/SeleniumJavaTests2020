package com.unitedcoder.regression.databasetest;


public class CategoryObject {
    private String cat_name;
    private String cat_desc;
    private int cat_parent_id;
    private int cat_image;
    private int per_ship;
    private String seoMetaTitle;
    private String seoMetaDescription;
    private String seoMetaKeyword;
    private int priority;
    private int status;

    public CategoryObject(String cat_name, String cat_desc, int cat_parent_id, int cat_image, int per_ship, String seoMetaTitle, String seoMetaDescription, String seoMetaKeyword, int priority, int status) {
        this.cat_name = cat_name;
        this.cat_desc = cat_desc;
        this.cat_parent_id = cat_parent_id;
        this.cat_image = cat_image;
        this.per_ship = per_ship;
        this.seoMetaTitle = seoMetaTitle;
        this.seoMetaDescription = seoMetaDescription;
        this.seoMetaKeyword = seoMetaKeyword;
        this.priority = priority;
        this.status = status;
    }

    public String getCat_name() {
        return cat_name;
    }

    public String getCat_desc() {
        return cat_desc;
    }

    public int getCat_parent_id() {
        return cat_parent_id;
    }

    public int getCat_image() {
        return cat_image;
    }

    public int getPer_ship() {
        return per_ship;
    }

    public String getSeoMetaTitle() {
        return seoMetaTitle;
    }

    public String getSeoMetaDescription() {
        return seoMetaDescription;
    }

    public String getSeoMetaKeyword() {
        return seoMetaKeyword;
    }

    public int getPriority() {
        return priority;
    }

    public int getStatus() {
        return status;
    }
}


