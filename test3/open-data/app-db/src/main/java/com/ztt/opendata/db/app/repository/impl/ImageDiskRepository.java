package com.ztt.opendata.db.app.repository.impl;

import com.ztt.opendata.db.app.model.Photo;
import com.ztt.opendata.db.app.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Repository("imageDiskRepository")
public class ImageDiskRepository implements ImageRepository {

    private final static String IMAGE_DIR = "images";

    @Value("${ztt.cameras.path}")
    private String camerasPath ;


    @Override
    public void save(Photo photo) {
        //System.out.println("Save "+photo.getName()+" in Disk");
        this.saveFile(photo);
    }

    private void saveFile(final  Photo photo){

        StringBuffer target = new StringBuffer(camerasPath);
        target.append(photo.getName()).append(File.separator);
        target.append(IMAGE_DIR).append(File.separator);

        // -- File Name
        target.append(photo.getName());
        target.append("_").append(photo.getDate().getTime()).append(".jpg");

        File file = new File(target.toString());
        if  (!file.getParentFile().exists()){
            // -- Si no existe el directorio  contenedor, se crea
            file.getParentFile().mkdirs();
        }

        this.deleteOldPhotos(file.getParentFile());

        try {
            InputStream in = new URL("http://"+photo.getUrl()).openStream();
            Files.copy(in, Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteOldPhotos(final File dir){
        File[] files = dir.listFiles();
        if (files.length > 10){
            int itemsToDelete = files.length - 10;
            for (File file: files){
                file.delete();
                itemsToDelete --;
                if (itemsToDelete == 0){
                    break;
                }
            }
        }
    }

}
