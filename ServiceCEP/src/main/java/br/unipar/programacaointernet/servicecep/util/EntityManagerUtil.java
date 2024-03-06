package br.unipar.programacaointernet.servicecep.util;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerUtil {

    private static EntityManagerFactory emf; //uma vez  só durante toda a aplicação
    private static EntityManager em; //cada requisição

    private EntityManagerUtil(){ }

    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf == null ){
            emf = Persistence.createEntityManagerFactory("HibernateMaven");
            System.out.println("conexão aberta");

        }
        return emf;
    }

    public static EntityManager getManeger(){
        if (em == null || !em.isOpen()){
            em = emf.createEntityManager();
            System.out.println("Entity Manager Aberta");
        }
        return em;
    }

    public static void closeEntityManagerFactory(){
        if(emf != null && emf.isOpen()){
            emf.close();
            System.out.println("Conexão Fechada");
        }
    }

    public static void main(String[] args){

        try{
            EntityManagerUtil.getEntityManagerFactory();
            EntityManagerUtil.closeEntityManagerFactory();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
