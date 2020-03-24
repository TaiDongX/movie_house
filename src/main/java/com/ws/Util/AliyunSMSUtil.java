package com.ws.Util;

import com.aliyun.oss.ClientException;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author 王朔
 * Description:
 * @date: 2020.03.23 10:04
 * @since JDK 1.8
 */
public class AliyunSMSUtil {

    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    static final String product = "Dysmsapi";
    /**
     * 产品域名,开发者无需替换
     */
    static final String domain = "dysmsapi.aliyuncs.com";

    /**
     * TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找，上方申请的)
     */
    static final String ACCESS_KEY_ID = "LTAI4FusZe3wH1ChUWpwVYKB";
    static final String ACCESS_KEY_SECRET = "6qr4uA8oFjGDT2gFePWFOBraUCypa3";

    public static void sendSms(String phoneNum, String code) throws ClientException, com.aliyuncs.exceptions.ClientException {
        DefaultProfile profile = DefaultProfile.getProfile("beijing", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "beijing");
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("SignName", "xiuPeiLian");
        request.putQueryParameter("TemplateCode", "SMS_179215960");
        request.putQueryParameter("TemplateParam", "{'code':'" + code + "'}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException | com.aliyuncs.exceptions.ClientException e) {
            e.printStackTrace();
        }
    }
}
