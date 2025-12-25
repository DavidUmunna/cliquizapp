package com.app.quizapp.config;
import jakarta.persistence.*;
public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("quizappPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void shutdown(){
        emf.close();
    }
}

