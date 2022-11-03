package com.example.manage.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.manage.entity.Files;
import com.example.manage.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 文件上传接口
 * */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileMapper fileMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;


    //文件上传
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        //获取原始名称
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        long size = file.getSize();
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断配置文件的目录是否存在，不存在，则创建一个新的文件目录
        if(uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        //文件后缀
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);


        //文件路径,获取文件的url
        String url;
        String md5;

        //把获取后的文件存到磁盘目录去
        file.transferTo(uploadFile);
        //当文件路径存在的时候再获取文件的md5
        md5 = SecureUtil.md5(uploadFile);
        //从数据库查询是否存在相同的记录
        Files files = getFileByMd5(md5);
        if(files != null){
            url = files.getUrl();
            //删除已存在文件，因为重复上传
            uploadFile.delete();
        }else {
            //数据库若不存在图片，则不删除刚上传的文件
            url = "http://localhost:9090/file/" + fileUUID;
        }



        //存储数据库
        Files saveFile = new Files();
        saveFile.setName(originalFileName);
        saveFile.setType(type);
        saveFile.setSize(size/1024);        //单位为kb
        saveFile.setUrl(url);
        saveFile.setMd5(md5);
        fileMapper.insert(saveFile);
        return url;
    }

    //下载接口 http://localhost:9090/file/{fileUUID};
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        //写出流格式
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }



    //方法,通过文件的md5查询
    private Files getFileByMd5(String md5){
        //查询文件的md5是否存在
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5", md5);
        List<Files> filesList = fileMapper.selectList(queryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}
