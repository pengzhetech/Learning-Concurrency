package com.javaman.concurrency.unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import static com.javaman.concurrency.unsafe.UnsafeUtil.getUnsafe;

/**
 * @author pengzhe
 * @date 2019-02-24 20:58
 * @description
 */

public class DynamicClass {

    private static byte[] getDynamicClassContent() throws IOException {
        File file = new File("/Users/pengzhe/code/Project/Learning-Concurrency/target/classes/com/javaman/concurrency/unsafe/Test.class");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] content = new byte[(int)file.length()];
        fileInputStream.read(content);
        fileInputStream.close();
        return content;
     }

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        byte[] dynamicClassContent = getDynamicClassContent();
        Class<?> aClass = (Class<?>) getUnsafe().defineClass(null, dynamicClassContent, 0,
                dynamicClassContent.length,ClassLoader.getSystemClassLoader(),
                new ProtectionDomain(null,null));
        Object value = aClass.getMethod("getValue").invoke(aClass.newInstance(), null);
        System.out.println(value);

    }
}
