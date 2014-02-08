/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hlx.juinfo.common.util;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;

import org.apache.log4j.Logger;

/**
 * ��EncodeUtil.java��ʵ���������ַ������ܽ�����
 * 
 * @author maxjcs 2012-9-3 ����2:28:06
 */
public class EncodeUtil {

    private static final Logger logger        = Logger.getLogger(EncodeUtil.class);

    private static String       strDefaultKey = "luckbuy.com";
    /** ���ܹ��� */
    private Cipher              encryptCipher = null;
    /** ���ܹ��� */
    private Cipher              decryptCipher = null;

    /**
     * ��byte����ת��Ϊ��ʾ16����ֵ���ַ����� �磺byte[]{8,18}ת��Ϊ��0813�� ��public static byte[] hexStr2ByteArr(String strIn) ��Ϊ�����ת������
     * 
     * @param arrB ��Ҫת����byte����
     * @return ת������ַ���
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // ÿ��byte�������ַ����ܱ�ʾ�������ַ����ĳ��������鳤�ȵ�����
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // �Ѹ���ת��Ϊ����
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // С��0F������Ҫ��ǰ�油0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬 ��public static String byteArr2HexStr(byte[] arrB) ��Ϊ�����ת������
     * 
     * @param strIn ��Ҫת�����ַ���
     * @return ת�����byte����
     * @throws Exception
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

    /**
     * Ĭ�Ϲ��췽����ʹ��Ĭ����Կ
     * 
     * @throws Exception
     */
    public EncodeUtil(){
        this(strDefaultKey);
    }

    /**
     * ָ����Կ���췽��
     * 
     * @param strKey ָ������Կ
     * @throws Exception
     */

    public EncodeUtil(String strKey){
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            Key key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception ex) {
            //
        }
    }

    /**
     * �����ֽ�����
     * 
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {

        try {
            return encryptCipher.doFinal(arrB);
        } catch (Exception ex) {
            logger.error("EncodeUtil.encrypt() is error! byte[]=" + arrB.toString(), ex);
        }
        return null;
    }

    /**
     * �����ַ���
     * 
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String encrypt(String strIn) {
        try {
            return byteArr2HexStr(encrypt(strIn.getBytes()));
        } catch (Exception ex) {
            logger.error("EncodeUtil.encrypt() is error! str=" + strIn, ex);
        }
        return "";
    }

    /**
     * �����ֽ�����
     * 
     * @param arrB ����ܵ��ֽ�����
     * @return ���ܺ���ֽ�����
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        try {
            return decryptCipher.doFinal(arrB);
        } catch (Exception ex) {
            logger.error("EncodeUtil.decrypt() is error! byte[]=" + arrB.toString(), ex);
        }
        return null;
    }

    /**
     * �����ַ���
     * 
     * @param strIn ����ܵ��ַ���
     * @return ���ܺ���ַ���
     * @throws Exception
     */
    public String decrypt(String strIn) {
        try {
            return new String(decrypt(hexStr2ByteArr(strIn)));
        } catch (Exception ex) {
            logger.error("EncodeUtil.decrypt() is error! str=" + strIn, ex);
        }
        return "";
    }

    /**
     * ��ָ���ַ���������Կ����Կ������ֽ����鳤��Ϊ8λ ����8λʱ���油0������8λֻȡǰ8λ
     * 
     * @param arrBTmp ���ɸ��ַ������ֽ�����
     * @return ���ɵ���Կ
     * @throws java.lang.Exception
     */
    private Key getKey(byte[] arrBTmp) throws Exception {
        // ����һ���յ�8λ�ֽ����飨Ĭ��ֵΪ0��
        byte[] arrB = new byte[8];
        // ��ԭʼ�ֽ�����ת��Ϊ8λ
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // ������Կ
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
    }
}
