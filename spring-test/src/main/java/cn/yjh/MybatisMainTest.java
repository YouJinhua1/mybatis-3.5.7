package cn.yjh;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cn.yjh.config.anno.MybatisConfig;
import cn.yjh.entity.User;
import cn.yjh.mapper.UserMapper;

public class MybatisMainTest {

    public static void main(String[] args) throws Exception {
        testAnnoConfig();
        testXmlConfig();
    }

    public static void testAnnoConfig(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);
        UserMapper userMapper = context.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey("1");
        System.out.println(user);
    }

    public static void testXmlConfig() throws Exception {
        String resource = "cn/yjh/config/xml/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey("1");
        System.out.println(user);
    }
}
