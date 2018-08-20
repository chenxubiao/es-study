package com.chenxb.esstudy.tika;

import com.chenxb.esstudy.util.IoUtil;
import org.apache.tika.Tika;

import java.io.File;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class SimpleTextExtractor {

    public static void main(String[] args) throws Exception {
        // Create a Tika instance with the default configuration
        Tika tika = new Tika();
        File resource = IoUtil.getResourceAsFile(TypeDetection.class, Consts.XML_PATH);
        // Parse all given files and print out the extracted text content
        String text = tika.parseToString(resource);
        System.out.print(text);


//        for (String file : args) {
//            String text = tika.parseToString(new File(file));
//            System.out.print(text);
//        }
    }

}
