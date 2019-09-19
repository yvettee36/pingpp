package com.pingpp.service;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.UserPic;
import com.pingpp.utils.CommonUtil;
import com.pingpp.utils.Const;

import java.util.HashMap;
import java.util.Map;

public class UserPicService {

    /**
     * 创建支付
     *
     * @param userPic
     * @return
     */
    public UserPic createPic(UserPic userPic) {
        Pingpp.appId = Const.APP_ID;
        Pingpp.apiKey = Const.APP_KEY;
        Pingpp.privateKey = Const.APP_PRIVATE_KEY;
       Pingpp.overrideApiBase("https://api.pingplusplus.com");

        try {
            //发起交易请求
            Map<String,Object> uploadPic = new HashMap<String,Object>();
            uploadPic.put("user","cxjTest");
            uploadPic.put("type","customer");
            uploadPic.put("operate_type","00");
            String imgToBase64 = CommonUtil.getImageStr("/Users/pingxx/IdeaProjects/pingpp/src/main/webapp/img/logo.png");
            uploadPic.put("pic",imgToBase64);
            uploadPic.put("pic_fmt","png");
            uploadPic.put("pic_type","101");

            System.out.println(Pingpp.privateKey);
            userPic = UserPic.upload(uploadPic);

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (ChannelException e) {
            e.printStackTrace();
        } catch (RateLimitException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }

        // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
        return userPic;
    }
}
