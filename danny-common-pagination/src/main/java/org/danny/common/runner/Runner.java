package org.danny.common.runner;

import org.mybatis.generator.api.ShellRunner;

/**
 * Created by Administrator on 2016/11/9.
 */
public class Runner {
    public static void generate() {
        String config = Runner.class.getClassLoader().getResource("generator.xml").getFile();
        String[] arg = { "-configfile", config, "-overwrite" };
        ShellRunner.main(arg);
        Pageable page = new Pageable(1,10);

    }
    public static void main(String[] args) {
      
    }
}
