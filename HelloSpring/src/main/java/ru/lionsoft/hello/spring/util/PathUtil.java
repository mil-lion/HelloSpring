/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.util;

/**
 *
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
public class PathUtil {
    
    public static String path(Class clazz, String fileanme) {
        return clazz.getPackageName()
                .replaceAll("\\.", "/")
                + "/" + fileanme;
    }
}
