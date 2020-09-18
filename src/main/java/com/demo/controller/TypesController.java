package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Types;
import com.demo.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypesController {

    @Autowired
    private TypesService typesService;


    @GetMapping("/getAll")
    public String getAll(){
        String types = JSONObject.toJSONString(typesService.selectAll());
        return types;
    }

    @PostMapping("/addTypes")
    public void addTypes(@RequestBody Types types){
        typesService.insert(types);
    }

    @PostMapping("/deleteTypesById")
    public int deleteTypesById(long id){

       return typesService.deleteByPrimaryKey(id);
    }
}
