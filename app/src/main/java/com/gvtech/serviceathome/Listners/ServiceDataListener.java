package com.gvtech.serviceathome.Listners;

import com.gvtech.serviceathome.models.Service;

public interface ServiceDataListener {

    void onChooseServiceItem(Service.ServiceItem serviceItem);
}
