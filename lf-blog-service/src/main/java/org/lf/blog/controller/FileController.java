package org.lf.blog.controller;

import org.lf.blog.bean.RespBean;
import org.lf.blog.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;

/**
 * create by LF
 */
@RestController
public class FileController {

    @Autowired
    WorkService workService;

    @RequestMapping("/file_upload")
    public RespBean upload(String workCode, String username, MultipartFile file, HttpServletRequest req) throws Exception{
        System.out.println("username数据："+username);
        //接收文件数据
        System.out.println(file.getContentType());//  image/jpeg   获取上传文件的类型
        System.out.println(file.getName());//image  获取file标签的name属性  <input type="file" name="image" >
        System.out.println(file.getOriginalFilename());//1.jpg   获取上传文件的名称

        workService.updateWorkFileByCode(workCode,username,file,req);
        return new RespBean("success", "上传成功!");
    }
    public RespBean download(HttpServletRequest req, HttpServletResponse resp, String filename) throws Exception {
        String realPath = req.getServletContext().getRealPath("/download");//获取下载文件的路径
        File file = new File(realPath, filename);//把下载文件构成一个文件处理   filename:前台传过来的文件名称

        //设置响应类型  ==》 告诉浏览器当前是下载操作，我要下载东西
        resp.setContentType("application/x-msdownload");
        //设置下载时文件的显示类型(即文件名称-后缀)   ex：txt为文本类型
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);

        //下载文件：将一个路径下的文件数据转到一个输出流中，也就是把服务器文件通过流写(复制)到浏览器端
        Files.copy(file.toPath(), resp.getOutputStream());//Files.copy(要下载的文件的路径,响应的输出流)
        return new RespBean("success", "下载成功!");
    }
}
