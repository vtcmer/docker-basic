package com.ztt.opendata.image.service.impl;

import com.ztt.opendata.image.commons.GifSequenceWriter;
import com.ztt.opendata.image.model.Photo;
import com.ztt.opendata.image.service.GeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class GeneratorGifService implements GeneratorService {

    private final static String IMAGE_DIR = "images";
    private final static String GIF_DIR = "gif";

    @Value("${ztt.cameras.path}")
    private String camerasPath ;

    @Override
    public void generate(Photo photo) {
        this.generateGif(photo);
    }

    private void generateGif(Photo photo){

        StringBuffer targetImagesDir = new StringBuffer(camerasPath);
        targetImagesDir.append(photo.getName()).append(File.separator);
        targetImagesDir.append(IMAGE_DIR).append(File.separator);

        StringBuffer targetGifDir = new StringBuffer(camerasPath);
        targetGifDir.append(photo.getName()).append(File.separator);
        targetGifDir.append(GIF_DIR).append(File.separator);

        File dir = new File(targetImagesDir.toString());
        if (dir.exists()){
            ImageOutputStream output = null;
            GifSequenceWriter writer = null;
            try {
                StringBuffer gifName = new StringBuffer(targetGifDir);
                gifName.append(photo.getName()).append(".gif");
                File gifFile = new File(gifName.toString());
                if (!gifFile.getParentFile().exists()){
                    gifFile.getParentFile().mkdirs();
                }
                output = new FileImageOutputStream(gifFile);

                File[] files = dir.listFiles();
                boolean isFirst = Boolean.TRUE;
                if (files.length > 0) {
                    for (File file : files) {
                        BufferedImage image = ImageIO.read(file);
                        if (isFirst) {
                            isFirst = Boolean.FALSE;

                            writer = new GifSequenceWriter(output, image.getType(), 250, true);
                            writer.writeToSequence(image);
                        }
                        writer.writeToSequence(image);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (output != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
