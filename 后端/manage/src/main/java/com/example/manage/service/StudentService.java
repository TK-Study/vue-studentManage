package com.example.manage.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.manage.entity.Student;
import com.example.manage.mapper.StudentMapper;
import org.springframework.stereotype.Service;

//注入springboot容器
@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> {

    public boolean saveStudent(Student student) {
        //使用内置的方法
//        if(student.getStuId() == null){
//            return save(student);       //mybatis-plus提供的方法，表示插入数据
//        }else {
//            return updateById(student);     //表示更新
//        }
        return saveOrUpdate(student);
    }


    //    @Autowired
//    private StudentMapper studentMapper;
//
//    //判断操作，对更新和添加数据操作进行判断，然后返回不同的方法
//    public int save(Student student) {
//        if (student.getStuId() == null) {     //如果stuId不存在，则证明是插入操作
//            return studentMapper.insert(student);
//        } else {
//            return studentMapper.update(student);      //否则就是更新操作
//        }
//    }
}
