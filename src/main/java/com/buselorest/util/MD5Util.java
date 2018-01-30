package com.buselorest.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    /**
     * Hash incoming String by md5 algorithm
     * @param st - string to be hashed
     * @return - hashed String
     */
    public static String md5Apache(String st) {
        return DigestUtils.md5Hex(st);
    }
}
