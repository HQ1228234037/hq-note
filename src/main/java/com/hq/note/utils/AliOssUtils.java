package com.hq.note.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * 阿里 oss 工具类
 *
 * @author HQ
 **/
@Component
public class AliOssUtils {

    @Value("${ali.oss.bucket}")
    private String bucket;
    @Value("${ali.oss.endpoint}")
    private String endpoint;
    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ali.oss.imageDomainName}")
    private String imageDomainName;

    /**
     * 文件上传oss
     *
     * @param file 待上传文件
     * @return 文件访问地址
     * @author HQ
     **/
    public String uploadFile(MultipartFile file) throws IOException {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int monthValue = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        String savePath = "note/" + year + "/" + monthValue + '/' + day + "/";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = uuid + suffix;

        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucket, savePath + fileName, file.getInputStream());

        // 关闭OSSClient。
        ossClient.shutdown();

        return imageDomainName + "/" + savePath + fileName;
    }

}
