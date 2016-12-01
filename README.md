

###准备工作

在应用集成微信登录以及分享之前，我们在[微信开放平台](https://open.weixin.qq.com/cgi-bin/index?t=home/index&lang=zh_CN&token=79b90f1a82ebf8eecfd25e57c43729df42c5f9b5)必须要个开发者账户

1.注册完之后创建一个移动应用
![这里写图片描述](http://img.blog.csdn.net/20161201151311629)

![这里写图片描述](http://img.blog.csdn.net/20161201151416392)

2.填写对应应用包名以及签名(md5)信息，填写完毕后，会有个审核过程，一般第二天就通过了。
![这里写图片描述](http://img.blog.csdn.net/20161201151552534)
（注:如何生成应用签名以及查看签名：http://blog.csdn.net/vroymond/article/details/53228331）
![这里写图片描述](http://img.blog.csdn.net/20161201151645977)

3.审核成功后获取到应用对应的AppId以及AppSercet 
![这里写图片描述](http://img.blog.csdn.net/20161201151717972)
(注:获取微信的登录与支付等功能需要付费认证开发者资格)

###集成微信分享
1.下载[jar包](https://res.wx.qq.com/open/zh_CN/htmledition/res/dev/download/sdk/Android_SDK_3.1.1.zip)，拷进项目中，并创建一个类用于放置我们申请到的AppId以及AppSercet
![这里写图片描述](http://img.blog.csdn.net/20161201151852964)
2.在项目包名下创建一个wxapi的包，并创建一个WXEntryActivity的类(微信分享以及登录必须要求)，该类继承activity并实现IWXAPIEventHandler接口，用于拿到分享的回调结果以及获取登录回调的授权码.
![这里写图片描述](http://img.blog.csdn.net/20161201151929379)

![这里写图片描述](http://img.blog.csdn.net/20161201151947262)
![这里写图片描述](http://img.blog.csdn.net/20161201152028801)
![这里写图片描述](http://img.blog.csdn.net/20161201152003316)

在Androidmianfest.xml中注册，并设置exported为true
![这里写图片描述](http://img.blog.csdn.net/20161201152109348)

3.调起一个简单的文本分享

	1）注册到微信，要使你的程序启动后微信终端能响应你的程序，必须在代码中向微信终端注册你的id
![这里写图片描述](http://img.blog.csdn.net/20161201152333455)
（注：最好判断当前手机是否有安装微信应用，以及检测当前手机微信版本是否是支持分享的微信版本）
![这里写图片描述](http://img.blog.csdn.net/20161201152235380)

	2)创建一个文本对象，发送请求到微信
![这里写图片描述](http://img.blog.csdn.net/20161201152452301)

	3)在WXEntryActivity中的onResp根据errCode值对照分享结果： 
![这里写图片描述](http://img.blog.csdn.net/20161201152536880)

（注：运行时需注意应用签名问题，运行项目的签名必须跟注册应用时填写的签名信息保持一致，才可调的起微信，为了方便测试，在build.gradle配置了debug使用了对外发布版本的签名信息，如何配置查看：http://www.cnblogs.com/niray/p/5242985.html）
![这里写图片描述](http://img.blog.csdn.net/20161201152611615)

测试结果：
![这里写图片描述](http://img.blog.csdn.net/20161201152636132)

4.图片分享，以及Url地址分享
基本步骤跟文本分享一致，只是发送请求的内容不同（相同步骤省略）

	1)发送图片请求
![这里写图片描述](http://img.blog.csdn.net/20161201152735961)

	2）分享Url 
![这里写图片描述](http://img.blog.csdn.net/20161201152814195)

效果展示：
![这里写图片描述](http://img.blog.csdn.net/20161201152839367)

###集成微信登录

一般第三方应用集成微信登录是要拿到微信账户的信息，作为第三方应用的快速登录
微信登录分为三个步骤：

- 调起微信登录之后获取到授权码code
- 根据这个code再去请求微信提供的接口：获取授权access_token 令牌和open_Id开发者标识

  接口：（其中APPID  SECRET CODE值分别对应的是申请到的appid app_secret和调起微信登录成赶回的code值）
  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code

- 根据这个令牌去访问微信提供的接口获取到用户个人信息。

  接口：（其中ACCESS_TOKEN令牌和OPENID对应第二步获取到的结果）
  https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID

1.调起微信登录，步骤跟微信分享类似，不同的是发送请求以及处理WXEntryActivity中的onResp的方法不同(相同步骤省略)

	1)发送请求
![这里写图片描述](http://img.blog.csdn.net/20161201153202310)

	2)回调结果处理
![这里写图片描述](http://img.blog.csdn.net/20161201153229405)

	3）获取用户信息
![这里写图片描述](http://img.blog.csdn.net/20161201153306290)

因为微信登录认证付费的原因就没拿原来申请的测试，用本人之前项目微信账户测试
效果展示：
![这里写图片描述](http://img.blog.csdn.net/20161201153420048)

源码地址（这个版本因微信账户申请原因微信登录不能通过，代码是可行的）：https://github.com/CTSN/Wechat2



