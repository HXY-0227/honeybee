# honeybee
## 项目简介
创建这个项目的初心是想做一个资金管理的系统，后来因为一些原因搁置。目前计划在原来的代码基础上，将springboot由1.x升级到2.x，并重新整合常用的分布式中间件，完善鉴权等功能，补充UT，搭建一个基础的web框架，以便随时开发web项目可用。

## 代码目录

`com.honeybee.common.annotation`主要用来写一下常用的注解，比如`@Cache`注解实现通过切面为业务代码做缓存

`com.honeybee.common.aop`主要写一些切面，对注解做补充实现

`com.honeybee.common.bean`用来定义各种实体类

`com.honeybee.common.configure`定义各种配置类

`com.honeybee.common.database`配置数据库

`com.honeybee.common.expection`全局异常以及自定义异常

`com.honeybee.common.filter`过滤器

以及service、controller以及dao层的包



