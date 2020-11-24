package sms.flow.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceRegistrarConfigure {

    private final Iterable<ServiceRegistrarAdapter> adapters;

    public ServiceRegistrarConfigure(ServiceRegistrarAdapter... adapters) {
        this(List.of(adapters));
    }

    public ServiceRegistrarConfigure(Collection<ServiceRegistrarAdapter> adapters) {
        this.adapters = new ArrayList<>(adapters);
    }

    public SmsSenderRegistrar configure() {
        SmsSenderRegistrarImpl registrar = new SmsSenderRegistrarImpl();
        for (ServiceRegistrarAdapter adapter : adapters) {
            adapter.configure(registrar);
        }

        return registrar;
    }
}
