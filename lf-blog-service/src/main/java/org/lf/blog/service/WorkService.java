package org.lf.blog.service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.lf.blog.bean.StayHungryWebpage;
import org.lf.blog.bean.Work;
import org.lf.blog.mapper.WorkMapper;
import org.lf.blog.utils.FtpUtil;
import org.lf.blog.utils.ImgUtil;
import org.lf.blog.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Created by LF on 2020/09/17.
 */
@Service
@Transactional
@EnableConfigurationProperties(StayHungryWebpage.class)
public class WorkService {
    @Autowired
    WorkMapper workMapper;
    @Autowired
    StayHungryWebpage stayHungryWebpage;
    /**
     * 持久化作品
     * @param work
     * @return
     */
    public Integer addNewWork(Work work){
        Long uid = Util.getCurrentUser().getId();
        work.setUid(uid);
        Long num = workMapper.getMaxWorkNum();
        if(num==null||num<1l){
            work.setWorkCode(1l);
        }else{
            work.setWorkCode(num+1l);
        }
        return workMapper.addNewWork(work);
    }

    /**
     * 获取当前最大作品编号
     * @return
     */
    public Long getMaxWorkNum(){
        return workMapper.getMaxWorkNum();
    }

    public List<Work> getWorkList(Integer page, Integer count){
        int start = (page - 1) * count;
        Long uid = Util.getCurrentUser().getId();
        return workMapper.getWorkByStateByUser(start, count, uid);
    }

    public int getWorkCountByStateByUser(){
        Long uid = Util.getCurrentUser().getId();
        return  workMapper.getWorkCountByStateByUser(uid);
    }

    public boolean deleteWorkByIds(String ids) {
        String[] split = ids.split(",");
        int result = workMapper.deleteWorkByIds(split);
        return result == split.length;
    }
    public boolean updateWorkFileByCode(String workCode, String username, MultipartFile file, HttpServletRequest req)throws Exception{
        //1.图片处理

        String filename = file.getOriginalFilename();//获取上传时的文件名称
        String extensionName = FilenameUtils.getExtension(filename);//getExtension(name):获取文件后缀名
        String imgName = workCode+"."+extensionName;
        String imgNameBig =workCode+"_big."+extensionName;
        File temFile = File.createTempFile("fatherIma", "temFile."+extensionName);

        FileUtils.copyInputStreamToFile(file.getInputStream(), temFile);
        BufferedImage originalImage = ImageIO.read(temFile);
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        InputStream resizeImageBig = ImgUtil.resizeImgInputStream(originalImage, type,stayHungryWebpage.getWorkBigImgWidth(),stayHungryWebpage.getWorkBigImgHeight());
        InputStream resizeImage = ImgUtil.resizeImgInputStream(originalImage, type,stayHungryWebpage.getWorkImgWidth(),stayHungryWebpage.getWorkImgHeight());
        //2.图片上传ftp服务器
        InputStream in= resizeImage;
        boolean flag = FtpUtil.uploadFile("byu6942970001.my3w.com", 21, "byu6942970001", "Rootliufei123", "/htdocs","/5", imgName, in);
        flag = FtpUtil.uploadFile("byu6942970001.my3w.com", 21, "byu6942970001", "Rootliufei123", "/htdocs","/5", imgNameBig, resizeImageBig);
        System.out.println(flag);
        return false;
    }
}
