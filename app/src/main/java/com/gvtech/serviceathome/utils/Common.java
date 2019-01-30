package com.gvtech.serviceathome.utils;

class Common {
    private static final Common ourInstance = new Common();

    static Common getInstance() {
        return ourInstance;
    }

    private Common() {
    }
}
