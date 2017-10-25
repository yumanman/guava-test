package com.mandy.servant;

import com.mandy.api.Service;

import java.util.ServiceLoader;

    public class ServiceInvoke {
        public static void main(String[] args) {
            ServiceLoader<Service> serviceLoader=ServiceLoader.load(Service.class);
            for(Service service:serviceLoader){
                service.show();
            }
        }
}
