# ihat_location
基于TDOA的UWB室内定位后台处理程序<br/>
### 文件结构<br>
遵循maven构建方式
### 入口函数
ihat_project.main<br>
### 运行方式
代码可以通过在根目录下运行<br>
`mvn package`<br>
或者<br>
`ant`<br>
或者<br>
`gradle shadow`
来生成jar包<br>
jar包的位置在target目录下，如果是gradle生成的则在`build\lib`下.注意jar包需和配置文件同级目录才可运行<br>
`java -jar ***.jar`<br>
即可运行项目<br>
### 导入eclipse<br>
项目本身就是兼容eclipse，可以直接导入
