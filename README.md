# 快递取件2.0

一个用于快速打开常用快递取件码和电商待取列表的 Android 工具。

作者：EyanLiu

## 下载

请前往 GitHub Releases 下载最新签名版 APK：

https://github.com/EidenLiu/pickup-code-launcher/releases/latest

## 已实现

- 快速打开菜鸟、淘宝和拼多多取件码
- 查看淘宝、拼多多、京东和小红书待取快递
- 对应 App 无法打开时自动尝试网页入口
- 支持应用长按快捷操作
- 支持将任意入口固定为独立桌面图标
- 支持五按钮 Android 桌面小组件
- 不读取短信、通知、相册或账号数据
- 不需要网络权限和后端服务

## Android 构建

需要 JDK 17 和 Android SDK 35：

```powershell
.\gradlew.bat assembleDebug
```

调试 APK 输出到：

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Android 发布版签名

发布前请创建自己的签名证书，并在后续版本中一直使用同一个证书：

```powershell
keytool -genkeypair -v -keystore release-keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias kuaidi_qujian
```

复制 `keystore.properties.example` 为 `keystore.properties`，填入签名信息，然后执行：

```powershell
.\gradlew.bat assembleRelease
```

签名文件和密码不能上传到公开仓库。以后更新应用必须继续使用同一个签名文件。

## 链接维护

应用使用第三方 App 的内部页面入口，这些入口并非稳定的开放 API。发布前应在安装了最新版对应 App 的真机上逐个测试。

## 开源许可

本项目使用 MIT License。

当前版本：2.0.0

最后核对日期：2026-09-11。
