package medicaldoctor.core;

import medicaldoctor.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class Global {

    public static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    public static SessionFactory buildSessionFactory() {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        return config.buildSessionFactory(serviceRegistry);
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }

}
