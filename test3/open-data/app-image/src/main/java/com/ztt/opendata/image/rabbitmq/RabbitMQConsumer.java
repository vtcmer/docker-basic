package com.ztt.opendata.image.rabbitmq;

import com.ztt.opendata.image.commons.GifSequenceWriter;
import com.ztt.opendata.image.model.Photo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${ztt.rabbitmq.queue}")
    public void recievedMessage(Photo photo) {
        System.out.println("Recieved Message From RabbitMQ IMAGE: " + photo.getName());
        this.processPhoto(photo);
        this.generateGif(photo);
    }

    private void generateGif(Photo photo){

        String  path = "/home/vtcmer/ztt/dev/docker/docker-basic/test3/open-data/cameras/";

        StringBuffer targetImagesDir = new StringBuffer(path);
        targetImagesDir.append(photo.getName()).append(File.separator);
        targetImagesDir.append("images").append(File.separator);


        StringBuffer targetGifDir = new StringBuffer(path);
        targetGifDir.append(photo.getName()).append(File.separator);
        targetGifDir.append("gif").append(File.separator);


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


    private void processPhoto(final  Photo photo){

        String  path = "/home/vtcmer/ztt/dev/docker/docker-basic/test3/open-data/cameras/";
        StringBuffer target = new StringBuffer(path);
        target.append(photo.getName()).append(File.separator);
        target.append("images").append(File.separator);

        // -- File Name
        target.append(photo.getName());
        target.append("_").append(photo.getDate().getTime()).append(".jpg");

        File file = new File(target.toString());
        if  (!file.getParentFile().exists()){
            // -- Si no existe el directorio  contenedor, se crea
            file.getParentFile().mkdirs();
        }

        this.deleteOldPhotos(file.getParentFile());



/*
        try {
            URL imageUrl = new URL("http://"+photo.getUrl());
            BufferedImage bufferedImage = ImageIO.read(imageUrl);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bos );
            byte[] data = bos.toByteArray();

            StringBuffer stringBuffer = new StringBuffer(path);
            stringBuffer.append(photo.getName()).append(".jpg");
            File file = new File(stringBuffer.toString());
            if (!file.exists()){
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        try {
            InputStream in = new URL("http://"+photo.getUrl()).openStream();
            Files.copy(in, Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
