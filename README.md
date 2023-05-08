# springcloud-service
spring cloud alibaba

说实话，微服务项目使用这些组件上真的挺傻逼的，各个版本之间需要契合地很严重，有时候功能不生效很大概率是jar包版本的问题，你还拿这东西办法。。。。

（1）nacos环境的搭建：
默认的nacos是没有开启鉴权的，需要手动调整配置文件，在nacos版本不同的情况下，开启鉴权的方式不同，例如我安装的nacos版本是2.2.0
只开启下面这项是没有用的：nacos.core.auth.enabled=true
还需填充这一项：nacos.core.auth.plugin.nacos.token.secret.key=VGhpc0lzTXlDdXN0b21TZWNyZXRLZXkwMTIzNDU2Nzg=

（2）idea构建微服务项目时，spring cloud alibaba是依赖于spring boot的，项目是正常的创建程序，但在maven的pom文件中，我们需要明确定义spring cloud alibaba的版本，spring boot和spring cloud alibaba是有版本对应的说法的，我们可以直接参考阿里巴巴提供给我们的云原生应用脚手架直接搭建，这是最简单的最不绕人的，不然版本对应很容易把你搞晕。
云原生应用脚手架地址：https://start.aliyun.com/

（3）Dubbo启动时会有一个qos服务，qos是Dubbo的在线运维命令，可以对服务进行动态的配置、控制和查询。默认的服务端口为22222，本机启动两个Dubbo实例时注意端口的占用问题。

（4）gateway问题：
gateway项目光引入Spring Cloud Gateway依赖是没有用的，还需要引入
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-loadbalancer</artifactId>
</dependency>
因为网关项目大概是要引入Nacos的服务注册和发现的，而Nacos是不兼容Spring Cloud Gateway自带的Ribbon的，如果不引入上述依赖，路由是不会生效的。

另外，Gateway项目是不能引入Spring Boot Web的，有冲突

（5）sentinel问题

sentinel dashboard启动后，簇点链路的监控上并没有立刻出现相关的接口信息，需要访问一次相关接口才可以。
