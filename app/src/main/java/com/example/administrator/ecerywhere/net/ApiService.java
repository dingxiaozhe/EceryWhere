package com.example.administrator.ecerywhere.net;




import com.example.administrator.ecerywhere.bean.VerifyCodeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ApiService {
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();
}
