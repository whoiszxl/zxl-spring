package com.whoiszxl.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import com.whoiszxl.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notBlank(path, "class path路径不能为空");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = classLoader.getResourceAsStream(path);
        if(is == null) {
            throw new FileNotFoundException(this.path + " 路径下的文件不存在");
        }
        return is;
    }
}
