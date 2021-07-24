package md5;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

/**
 * TODO: 测试md5加密算法
 *
 * @author leyuan
 * @date 2021/7/22 15:55
 */
public class md5Test {
    @Test
    public void md5Test() {
        String pwd = DigestUtil.md5Hex("admin");
        System.out.println(pwd);
    }
}
