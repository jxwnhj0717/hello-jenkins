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
1. 通过windows10 + wsl + Docker Desktop + Docker Jenkins的方式部署，会导致vmmem占用4G+的内存，通过腾讯云Linux主机 + Docker Jenkins，只需要占用1G内存。
2. jenkins只支持通过https访问github，从腾讯云节点拉取源码时失败概率非常大。
3. junit不配置skipPublishingChecks=true，活动中看不到测试记录。

## 参考
+ [以Docker的方式安装Jenkins](https://www.jenkins.io/doc/book/installing/docker/)
+ [在pipeline中使用Docker-1](https://www.jenkins.io/zh/doc/book/pipeline/docker/)
+ [在pipeline中使用Docker-2](https://docs.cloudbees.com/docs/admin-resources/latest/plugins/docker-workflow)

## 延伸
1. 单元测试多语言支持。比如用python编写excel配置表的测试用例，能否收集到结果。

## 其他
+ 安装docker registry：docker run -d -p 5000:5000 --name registry registry:2

+ 安装docker registry ui：docker run -d -p 5001:80 joxit/docker-registry-ui