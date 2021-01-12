package com.zsk.login.repo

import androidx.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.zsk.common.model.SingleLiveData
import com.zsk.common.network.support.serverData
import com.zsk.login.net.LoginService
import com.zsk.login.net.LoginReqBody
import com.zsk.login.net.LoginRsp
import com.zsk.login.net.RegisterRsp
import com.zsk.service.network.onBizError
import com.zsk.service.network.onBizOK
import com.zsk.service.network.onFailure
import com.zsk.service.network.onSuccess

/**
 * @创建日期: 2020/12/31 10:59
 * @作者: zsk
 * @描述: 描述一下方法的作用
 */
class LoginRepo(private val service: LoginService) : ILoginResource {

    private val _registerRsp = SingleLiveData<RegisterRsp>()
    private val _loginRsp = SingleLiveData<LoginRsp>()

    override val registerRsp: LiveData<RegisterRsp> = _registerRsp
    override val loginRsp: LiveData<LoginRsp> = _loginRsp


    override suspend fun checkRegister(mobi: String) {
        service.isRegister(mobi)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功
                onBizError { code, message ->
                    LogUtils.w("是否注册 BizError $code,$message")
                }
                onBizOK<RegisterRsp> { code, data, message ->
                    _registerRsp.value = data
                    LogUtils.i("是否注册 BizOK $data")
                    return@onBizOK
                }
            }.onFailure {
                LogUtils.e("是否注册 接口异常  ${it.message}")
            }

    }

    override suspend fun requestLogin(body: LoginReqBody) {
        service.login(body)
            .serverData()
            .onSuccess {
                //只要不是接口响应成功
                onBizError { code, message ->
                    LogUtils.w("登录接口 BizError $code,$message")
                }
                onBizOK<LoginRsp> { code, data, message ->
                    _loginRsp.value=data
                    LogUtils.i("登录接口 BizOK $data")
                }

            }.onFailure {
                LogUtils.e("登录接口 接口异常  ${it.message}")
            }
    }

}