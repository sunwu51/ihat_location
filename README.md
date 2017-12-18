# ihat_location
基于TDOA的UWB室内定位后台处理程序<br/>
## 描述
该版本基于`SpringBoot`做了整合，早期版本可以查看`releasev1`
### 文件结构<br>
遵循maven构建方式
### 运行方式
代码可以通过在根目录下运行
```
mvn spring-boot:run
```
### 打包时需要跳过test
```
mvn package -DskipTests=true
```
### 运行jar
和Config.json在同一文件夹下
```
java -jar xxx.jar
```