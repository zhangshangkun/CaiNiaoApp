package com.zsk.common.network.support;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.zsk.common.network.config.CnConfigKtKt;

/**
 * @创建日期: 2020/12/28 15:35
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
public class CnUtils {
    private CnUtils() {

    }

    //中文转unicode
    public static String unicodeEncode(String string) {
       return string;
    }

    //unicode转中文
    public static String unicodeDecode(String string) {
         return string;
    }

    /**
     * 解析返回的data数据
     */
    public static String decodeData(String dataStr) {
        //java代码，无自动null判断，需要自行处理
        if (dataStr != null) {
            return new String(EncryptUtils.decryptBase64AES(dataStr.getBytes(), CnConfigKtKt.ENT_CONFIG_APPKEY.getBytes(), "" +
                    "AES/CBC/PKCS7Padding", "J#y9sJesy*5HmqLq".getBytes()));
        } else {
            return null;
        }
    }
}
