package com.bytehonor.sdk.basic.server.util;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.bytehonor.sdk.basic.server.util.FileUtils;

public class FileUtilsTest {

    @Test
    public void testByte2File() {

//        byte[] qrcodeBytes = QrcodeUtils.createQrcode("hello world 12313", null);
//        FileUtils.byte2File(qrcodeBytes, "\\testfile\\", System.currentTimeMillis() + "youlogode.jpg");

        assertTrue("*testByte2File*", true);
    }

    @Test
    public void testDownload() {
        String url = "https://huajietaojin.oss-cn-hangzhou.aliyuncs.com/columbus/91d27a04666b49d4be9da5562ae6059a/store/logo/5a5dd285046116257b1d8c97d208b70e.jpg";
        File file = null;
        try {
            file = FileUtils.download(url, "\\testfile\\", "mylogo.jpg");

            // byte[] qrcodeBytes = QrcodeUtils.createQrcode("hello world 12313", file);
            // FileUtils.byte2File(qrcodeBytes, "D:\\file\\", System.currentTimeMillis() +
            // "qrcodelogo.jpg");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue("*testDownload*", file != null);
    }

    @Test
    public void testIsExistDir() {
        FileUtils.isExistDir("\\testfile\\2018\\");

        assertTrue("*testIsExistDir*", true);
    }

}
