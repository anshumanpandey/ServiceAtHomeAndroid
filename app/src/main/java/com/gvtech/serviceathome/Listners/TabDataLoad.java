package com.gvtech.serviceathome.Listners;

public class TabDataLoad {

    private TabDataListener tabDataListener;
    public TabDataLoad(TabDataListener listener){
        this.tabDataListener = listener;
    }

    public void setDataLoad(Object o){
        this.tabDataListener.noDataLoad(o);
    }
}
