package com.example.employeecrudapp.services;

import com.example.employeecrudapp.dao.empdaoimp;
import com.example.employeecrudapp.entity.employee;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class service {
    private empdaoimp helper;{}
    public service(empdaoimp empdaoimp){
        helper=empdaoimp;
    }
    public List<employee> getall(){
        return helper.getallinfo();
    }
    public List<employee>getone(int id){
        return helper.getone(id);
    }
    @Transactional
    public void insert(employee emp){
        helper.saveinfo(emp);
    }
    @Transactional
    public void update(int id,String name){
        helper.update(id,name);
    }
    @Transactional
    public void partupdate(int id, Map<String,Object> mape) throws JsonMappingException {
        helper.partupdate(id,mape);
}
    }
