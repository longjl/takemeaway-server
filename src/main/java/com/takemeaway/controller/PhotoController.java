package com.takemeaway.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import com.takemeaway.util.PropertiesContent;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 图片业务处理
 * Created by longjianlin on 14/12/19.
 */
public class PhotoController extends Controller {
    private static final String TAG = "PhotoController";
    private Logger logger = Logger.getLogger(PhotoController.class);

    public void index() {
    }

    public void uploadPhoto() {
        logger.info(TAG + "->uploadPhoto");

        PropertiesContent pc = new PropertiesContent("photo");
        String photo_root = pc.get("photo_root");
        File file = new File(photo_root);
        if (!file.exists()) {
            file.mkdirs();
            logger.info(TAG + "-> uploadPhoto ->photo_root mkdirs");
        }
        try {
            List<UploadFile> uploadFiles = getFiles(photo_root);
            if (uploadFiles != null && uploadFiles.size() > 0) {
                for (UploadFile uf : uploadFiles) {
                    File f = uf.getFile();
                    f.renameTo(new File(photo_root + genFileName(f.getName())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirect("/");
    }

    /**
     * 生成不重复的图片名称。暂时使用时间戳
     */
    public String genFileName(final String photoName) {
        return UUID.randomUUID() + photoName.substring(photoName.lastIndexOf("."), photoName.length());
    }

}
