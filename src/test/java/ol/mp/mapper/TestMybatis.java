package ol.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ol.mp.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatis {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setAge(30);
        user.setEmail("21@itcast.cn");
        user.setUsername("caocao");
        user.setName("曹操");
        user.setPassword("123456");
        int result = this.userMapper.insert(user);
        System.out.println("result => " + result);
        System.out.println("id => " + user.getId());
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L);
        user.setAge(19);
        user.setPassword("666666");
        int result = this.userMapper.updateById(user);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(20);
        user.setPassword("888888");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "lisi");
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testUpdate2() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("age", 21).set("password", "321654").eq("username", "lisi");
        int result = this.userMapper.update(null, userUpdateWrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteById() {
        int result = this.userMapper.deleteById(7L);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        map.put("password", "888888");
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result => " + result);
    }

    @Test
    public void testDelete() {
        //用法1
/*        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username","caocao").eq("password","123456");*/
        //用法2
        User user = new User();
        user.setPassword("123456");
        user.setUsername("sunqi");
        QueryWrapper<User> wrapper = new QueryWrapper(user);
        int result = this.userMapper.delete(wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = this.userMapper.deleteBatchIds(Arrays.asList(9L, 10L));
        System.out.println("result => " + result);
    }

    @Test
    public void testSelectById() {
        User user = this.userMapper.selectById(2L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> user = this.userMapper.selectBatchIds(Arrays.asList(3L, 2L, 10L));
        for (User user1 : user) {
            System.out.println(user1);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "lisi");
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age", 20);  //age > 20
        int result = this.userMapper.selectCount(wrapper);
        System.out.println("result => " + result);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "it");
        List<User> user = this.userMapper.selectList(wrapper);
        for (User user1 : user) {
            System.out.println(user1);
        }
    }

    @Test
    public void testSelectPage() {
        Page<User> page = new Page<>(1, 1);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("email", "it");
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数:" + iPage.getTotal());
        System.out.println("数据总页数:" + iPage.getPages());
        System.out.println("当前页数:" + iPage.getCurrent());
        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }
}

