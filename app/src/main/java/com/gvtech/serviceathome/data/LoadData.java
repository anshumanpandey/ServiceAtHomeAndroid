package com.gvtech.serviceathome.data;

import com.gvtech.serviceathome.models.Availability;
import com.gvtech.serviceathome.models.BusinessService;
import com.gvtech.serviceathome.models.Gallery;
import com.gvtech.serviceathome.models.Service;
import com.gvtech.serviceathome.models.ServiceItem;

import java.util.ArrayList;

public class LoadData {

    public static ArrayList<Service> loadServiseData(){
        ArrayList<Service> services = new ArrayList<>();
        for(int i =0; i < 30; i++){
            Service service = new Service("123"+i,"Demo main service "+i, "https://ps.w.org/adrotate/assets/banner-772x250.jpg");
            services.add(service);
        }
        return services;
    }
    public static ArrayList<ServiceItem> loadServiseItemData(){
        ArrayList<ServiceItem> services = new ArrayList<>();
        for(int i =0; i < 30; i++){
            ServiceItem service = new ServiceItem("123"+i,
                    "Demo service "+i,
                    "https://ps.w.org/adrotate/assets/banner-772x250.jpg",
                    "This is demo description " + i,
                    4.5F);
            services.add(service);
        }
        return services;
    }

    public static ArrayList<BusinessService> loadBusinessServiseItemData(){
        ArrayList<BusinessService> services = new ArrayList<>();
        for(int i =0; i < 10; i++){
            BusinessService service = new BusinessService("1234",false,"this is demo ","12.2"+i);
            services.add(service);
        }
        return services;
    }

    public static ArrayList<Availability> loadAvailibilityItemData(){
        ArrayList<Availability> availabilities = new ArrayList<>();
        Availability sunday = new Availability("123","Sunday",10.00F,11.00F);
        Availability monday = new Availability("123","Monday",10.00F,11.00F);
        Availability tuesday = new Availability("123","Tuesday",10.00F,11.00F);
        Availability wednesday = new Availability("123","Wednesday",10.00F,11.00F);
        Availability thursday = new Availability("123","Thursday",10.00F,11.00F);
        Availability friday = new Availability("123","Friday",10.00F,11.00F);
        Availability saturday = new Availability("123","Saturday",10.00F,11.00F);
        availabilities.add(sunday);
        availabilities.add(monday);
        availabilities.add(tuesday);
        availabilities.add(wednesday);
        availabilities.add(thursday);
        availabilities.add(friday);
        availabilities.add(saturday);
        return availabilities;
    }

    public static ArrayList<Gallery> loadGalleryItemData(){
        ArrayList<Gallery> galleries = new ArrayList<>();
        Gallery a = new Gallery("1234","https://www.leighday.co.uk/getmedia/192a6580-0849-448c-a212-255caf3f8279/Beauty-parlour-syndrome.aspx?width=612&height=459&ext=.jpg");
        galleries.add(a);
        Gallery b = new Gallery("1234","https://weddingplz-res.cloudinary.com/image/upload/f_auto,fl_lossy/v1/live/vendor_portfolio/4/31067/New-Eves-Beauty-Parlour-4944-5-weddingplz.jpg");
        galleries.add(b);
        Gallery c = new Gallery("1234","http://cdn3.dealnyou.in/img/original/3dreamstime_xxl_17300351.jpg");
        galleries.add(c);
        Gallery d = new Gallery("1234","https://weddingplz-res.cloudinary.com/image/upload/f_auto,fl_lossy/v1/live/vendor_portfolio/4/31067/New-Eves-Beauty-Parlour-4944-5-weddingplz.jpg");
        galleries.add(d);
        Gallery e = new Gallery("1234","http://cdn3.dealnyou.in/img/original/3dreamstime_xxl_17300351.jpg");
        galleries.add(e);
        Gallery f = new Gallery("1234","https://www.leighday.co.uk/getmedia/192a6580-0849-448c-a212-255caf3f8279/Beauty-parlour-syndrome.aspx?width=612&height=459&ext=.jpg");
        galleries.add(f);

        return galleries;
    }

}
