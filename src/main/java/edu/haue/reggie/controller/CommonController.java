package edu.haue.reggie.controller;

import edu.haue.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
//        log.info(file.toString());
        //使用uuid.jpg的文件名存储文件
        String originalFilename = file.getOriginalFilename();
        String filename = UUID.randomUUID().toString();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        filename += substring;
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            //file是一个临时文件，需要转存到指定位置
            file.transferTo(new File(basePath+filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(filename);
    }

    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        FileInputStream fis = null;
        ServletOutputStream outputStream = null;
        try {
            //输入流 输入到内存
            fis = new FileInputStream(new File(basePath+name));
            //输出流 写入到客户端页面,展示图片
            outputStream = response.getOutputStream();
            //设置响应格式为图片
            response.setContentType("image/jpeg");
            int readCount = 0;
            byte[] bytes = new byte[1024];
            while ((readCount=fis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, readCount);
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
