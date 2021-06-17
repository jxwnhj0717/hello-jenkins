## 功能

### 阶段一：构建项目
1. 在docker环境中执行，将gradle_home挂载成volume，防止重复下载依赖
2. 通过gradle build命令完成项目的编译、测试、打包