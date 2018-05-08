package Repositories;

import Persistence.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
                settings.put("hibernate.connection.url", "jdbc:sqlite:C:/Users/Mars/IdeaProjects/Blood Donation ISS App/database/ISSBloodDonation.db");
                settings.put("hibernate.dialect", "com.enigmabridge.hibernate.dialect.SQLiteDialect");
                settings.put("hibernate.connection.CharSet", "utf8");
                settings.put("hibernate.connection.characterEncoding", "utf8");
                settings.put("hibernate.connection.useUnicode", "true");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");
                settings.put("hibernate.connection.autocommit", "true");

                registryBuilder.applySettings(settings);

                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(AsistentEntity.class)
                        .addAnnotatedClass(CentruTransfuziiEntity.class)
                        .addAnnotatedClass(CerereEntity.class)
                        .addAnnotatedClass(DetaliiCerereEntity.class)
                        .addAnnotatedClass(DonatieEntity.class)
                        .addAnnotatedClass(DonatorEntity.class)
                        .addAnnotatedClass(MedicEntity.class)
                        .addAnnotatedClass(NotificareEntity.class)
                        .addAnnotatedClass(PacientEntity.class)
                        .addAnnotatedClass(SpitalEntity.class)
                        .addAnnotatedClass(UnitateSanguinaEntity.class);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
