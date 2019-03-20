package com.xxin.chat.controller;

import com.xxin.chat.entity.Doctor;
import com.xxin.chat.repository.DoctorRepository;
import com.xxin.chat.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @GetMapping("/list")
    public ResponseResult getDocList(@RequestParam("token")String token){
        List<Doctor> doctors =  doctorRepository.findAll();
        ResponseResult result = new ResponseResult();
        result.setData(doctors);
        result.setCode(200);
        result.setMsg("获取医师列表");
        return result;
    }
}
