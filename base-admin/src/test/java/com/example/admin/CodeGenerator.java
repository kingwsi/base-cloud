package com.example.admin;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author wangshu
 * @Description TODO
 * @CreateTime 2021/03/25 14:16:00
 */
@Slf4j
public class CodeGenerator {
    public static void main(String[] args) {
//        CodeGeneratorSupport codeGeneratorSupport = new CodeGeneratorSupport("SYS_API_WHITELIST", "ApiWhitelist");
        CodeGeneratorSupport codeGeneratorSupport = new CodeGeneratorSupport("sys_dictionaries", "Dictionary");
        codeGeneratorSupport.generateCommonModule();
        codeGeneratorSupport.generateServiceModule();
        codeGeneratorSupport.generateControllerModule();
    }
}
