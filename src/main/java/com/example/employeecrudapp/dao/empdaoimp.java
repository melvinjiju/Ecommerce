package com.example.employeecrudapp.dao;

import com.example.employeecrudapp.entity.employee;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class empdaoimp implements empdao{
    private EntityManager entityManager;
    private ObjectMapper obj;
    public empdaoimp(EntityManager entityManager, ObjectMapper obj){
        this.entityManager=entityManager;
        this.obj=obj;

    }


    @Override
    public List<employee> getallinfo() {
        TypedQuery<employee>query=entityManager.createQuery("from employee",employee.class);
        return query.getResultList();
    }

    @Override
    public List<employee> getone(int id1) {
        TypedQuery<employee> query=entityManager.createQuery("from employee where id =:data", employee.class);
        query.setParameter("data",id1);
        return query.getResultList();
    }

    @Override
    public void saveinfo(employee emp) {
        entityManager.persist(emp);
    }

    @Override
    public void update(int id,String name) {
       employee emp = entityManager.find(employee.class,id);
       emp.setLastname((name));
    }

    @Override
    public void partupdate(int id, Map<String, Object> mape) throws JsonMappingException {
        employee emp = entityManager.find(employee.class,id);
        obj.updateValue(emp,mape);

    }


}
