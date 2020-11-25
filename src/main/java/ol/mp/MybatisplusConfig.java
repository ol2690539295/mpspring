package ol.mp;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("ol.mp.mapper")
public class MybatisplusConfig {
    @Bean   //配置分页插件
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

