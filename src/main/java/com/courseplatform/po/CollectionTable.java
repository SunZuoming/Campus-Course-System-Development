package com.courseplatform.po;

public class CollectionTable {
    private String collectno;

    private String collectionno;

    private String collectiontype;

    private String collector;

    private String collecttime;

    public String getCollectno() {
        return collectno;
    }

    public void setCollectno(String collectno) {
        this.collectno = collectno == null ? null : collectno.trim();
    }

    public String getCollectionno() {
        return collectionno;
    }

    public void setCollectionno(String collectionno) {
        this.collectionno = collectionno == null ? null : collectionno.trim();
    }

    public String getCollectiontype() {
        return collectiontype;
    }

    public void setCollectiontype(String collectiontype) {
        this.collectiontype = collectiontype == null ? null : collectiontype.trim();
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector == null ? null : collector.trim();
    }

    public String getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(String collecttime) {
        this.collecttime = collecttime == null ? null : collecttime.trim();
    }
}