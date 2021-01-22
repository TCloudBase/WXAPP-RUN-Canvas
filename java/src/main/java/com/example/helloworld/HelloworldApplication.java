
package com.example.helloworld;

import ch.qos.logback.core.OutputStreamAppender;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.QRCode;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

// import java.lang.Thread;

@SpringBootApplication
public class HelloworldApplication {


    @RestController
    static class HelloworldController {



        Map<EncodeHintType,Object>initConfig(){
            Map<EncodeHintType,Object> config = new HashMap<EncodeHintType,Object>();
            config.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);   //纠错
            config.put(EncodeHintType.MARGIN, 1);
            return config;
        }
        MatrixToImageConfig initColor(){
            MatrixToImageConfig conlor = new MatrixToImageConfig(0xFF000002, 0xFFFFFFFF);
            return conlor;
        }
        byte[]  funCreate(String str) throws WriterException, IOException {
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,300,350,initConfig());
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix,initColor());

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "png", os);
            byte b[] = os.toByteArray();
            byte[] res = Base64.encodeBase64(b);//转化成base64返回图片。

            return res;
        }

        @GetMapping("/")
        byte[] hello(@RequestParam("inputValue") String str) throws WriterException, IOException {
            if (str==""){
                str="https://developers.weixin.qq.com/miniprogram/dev/wxcloud/guide/container/";
            }
            byte[] binary=funCreate(str);
            System.out.println(binary);
            return binary;
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(HelloworldApplication.class, args);
    }

}

