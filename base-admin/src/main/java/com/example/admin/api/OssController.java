package com.example.admin.api;

import com.example.admin.common.bean.ResponseData;
import com.example.service.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * description: OssController <br>
 * date: 2020/8/13 15:27 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "对象存储")
@RestController
@RequestMapping("/api/oss")
public class OssController {

    private final OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    @PostMapping("/image")
    public ResponseData uploadFile(@RequestParam("file") MultipartFile file){
//        String path = ossService.uploadImage(file);
        String path = "https://vant-admin.oss-cn-hangzhou.aliyuncs.com/c749e499-ef95-49b1-9336-c2d1c2267552.png";
        if (StringUtils.isEmpty(path)) {
            return ResponseData.FAIL();
        }
        return ResponseData.OK(path);
    }
}
