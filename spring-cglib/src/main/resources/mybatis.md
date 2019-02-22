1.如何获取数据源:去解析mybatis-config.xml文件,然后加载到Configuration
```
org.apache.ibatis.session.SqlSessionFactoryBuilder.build(java.io.InputStream)
    org.apache.ibatis.builder.xml.XMLConfigBuilder
        org.apache.ibatis.builder.xml.XMLConfigBuilder.parse
            org.apache.ibatis.builder.xml.XMLConfigBuilder.parseConfiguration #解析配置文件，读取到mapper的配置（package,resource,url,class）
                org.apache.ibatis.session.SqlSessionFactoryBuilder.build(org.apache.ibatis.session.Configuration)
                    org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
```

2.sql执行过程
```
 org.apache.ibatis.session.defaults.DefaultSqlSessionFactory.openSession()
    org.apache.ibatis.session.defaults.DefaultSqlSession
        org.apache.ibatis.binding.MapperRegistry.getMapper #获取mapper对象
            org.apache.ibatis.binding.MapperProxyFactory.newInstance() #生成一个jdk动态代理类
                org.apache.ibatis.binding.MapperProxy.invoke #MapperProxy类实现InvocationHandler接口
                    org.apache.ibatis.binding.MapperMethod.execute #具体的执行方法
                        org.apache.ibatis.session.SqlSession.selectOne() #执行查询selectOne
                            org.apache.ibatis.session.defaults.DefaultSqlSession.selectList() #底层是执行selectList方法
                                org.apache.ibatis.executor.BaseExecutor.query() #执行query()方法
                                    org.apache.ibatis.mapping.MappedStatement.getBoundSql #获取执行的sql语句
                                        org.apache.ibatis.executor.BaseExecutor.queryFromDatabase #查询数据
```

