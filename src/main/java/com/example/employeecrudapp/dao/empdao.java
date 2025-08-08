package com.example.employeecrudapp.dao;

import com.example.employeecrudapp.entity.employee;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.List;
import java.util.Map;

public interface empdao {
    public List<employee> getallinfo();
    public List<employee>getone(int id);
    public void saveinfo(employee emp);
    public void update(int id,String name);
    public void partupdate(int id, Map<String,Object>mape) throws JsonMappingException;
}
