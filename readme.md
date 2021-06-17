## 功能

### 阶段一：构建项目
1. 在docker环境中执行，避免在jenkins环境中安装各种软件。将gradle_home挂载成volume，防止重复下载依赖。
2. 通过gradle build命令完成项目的编译、测试、打包。
3. 通过stash暂存构建产物，在下一阶段使用。
4. 在post阶段中记录测试和构建结果，可以在blue ocean的活动记录中查看。

### 阶段二：构建镜像并上传
1. 由于执行环境不在docker，需要通过unstash获取上一阶段构建的产物。
2. docker.build()构建镜像，构建文件为jenkins/Dockerfile，构建下上文目录为build。
3. docker.withRegistry()向Docker Registry注册镜像，默认注册到https://hub.docker.com/ ，registryCredential配置访问Registry的用户名密码。

## 问题
1. jenkins只支持通过https访问github，从腾讯云节点拉取源码时失败概率非常大。
2. junit不配置skipPublishingChecks=true，活动中看不到测试记录。

## 参考
+ https://www.jenkins.io/doc/book/pipeline/docker/
+ https://docs.cloudbees.com/docs/admin-resources/latest/plugins/docker-workflow
