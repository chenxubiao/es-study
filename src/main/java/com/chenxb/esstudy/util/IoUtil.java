package com.chenxb.esstudy.util;

import java.io.File;
import java.net.URL;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class IoUtil {
    public static File getResourceAsFile(Class<?> clazz, String name) {
        if (clazz != null && name != null) {
            URL url = clazz.getResource(name);
            if (url != null) {
                String file = url.getFile();
                if (file != null && file.length() > 0) {
                    return new File(file);
                }
            }
        }

        return null;
    }
}
