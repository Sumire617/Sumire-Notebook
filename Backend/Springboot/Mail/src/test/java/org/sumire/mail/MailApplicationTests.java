package org.sumire.mail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.sumire.mail.model.CodeStorage;
import org.sumire.mail.utils.CodeUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MailApplicationTests {

    @Test
    public void testGenerateCode() {
        String code = CodeUtil.generateCode();
        assertTrue(code.matches("\\d{6}"));  // 必须是6位数字
    }

    @Test
    public void testCodeStorage() {
        String email = "1667312433@qq.com";
        String code = "123456";

        CodeStorage.saveCode(email, code);
        assertTrue(CodeStorage.verifyCode(email, code));  // 验证正确码
        assertFalse(CodeStorage.verifyCode(email, "000000"));  // 错误码
        assertFalse(CodeStorage.verifyCode("wrong@email.com", code));  // 错误邮箱
    }

}
