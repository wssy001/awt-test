package com.example.awttest;

import org.springframework.aot.hint.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import static java.awt.RenderingHints.*;
import static java.awt.font.TextAttribute.*;

@SpringBootApplication
@ImportRuntimeHints(value = {ProjectRuntimeHintRegister.class})
public class AwtTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwtTestApplication.class, args);
        try {
            ClassPathResource nukaCodeBGResource = new ClassPathResource("image/nukaCodeBG.png");
            File file = new File("/root/test.png");
            if (!file.exists() && !file.createNewFile()) {
                System.out.println("file create failed!");
                return;
            }

            InputStream inputStream = nukaCodeBGResource.getInputStream();
            BufferedImage image = ImageIO.read(inputStream);
            Graphics2D pen = image.createGraphics();
            HashMap<TextAttribute, Object> hm = new HashMap<>();
            hm.put(TextAttribute.SIZE, 130.34);
            hm.put(WEIGHT, WEIGHT_REGULAR);
            hm.put(TextAttribute.TRACKING, TRACKING_TIGHT);
            hm.put(TextAttribute.FAMILY, "Share-TechMono Regular");
            Font font = new Font(hm);
            pen.setFont(font);
            pen.setColor(Color.WHITE);
            pen.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            pen.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
            pen.drawString("12345678", 1021, 485);
            pen.drawString("12345678", 1072, 637);
            pen.drawString("12345678", 1186, 779);

            HashMap<TextAttribute, Object> hm2 = new HashMap<>();
            hm2.put(TextAttribute.UNDERLINE, 2);
            hm2.put(TextAttribute.SIZE, 50.27);
            hm2.put(TextAttribute.WEIGHT, WEIGHT_BOLD);
            hm2.put(TextAttribute.FAMILY, "Microsoft YaHei");
            Font font2 = new Font(hm2);
            pen.setFont(font2);
            pen.setColor(new Color(216, 206, 108));
            ImageIO.write(image, "png", file);
            image.flush();
            System.out.println("image create at:" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
