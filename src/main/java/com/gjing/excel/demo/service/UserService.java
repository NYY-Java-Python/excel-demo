package com.gjing.excel.demo.service;

import cn.gjing.tools.common.util.RandomUtil;
import com.gjing.excel.demo.entity.User;
import com.gjing.excel.demo.enums.GenderEnum;
import com.gjing.excel.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Gjing
 **/
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 保存用户
     * @param num 保存数量
     */
    public void saveUser(int num) {
        for (int i = 0; i < num; i++) {
            userRepository.saveAndFlush(User.builder()
                    .userName("用户" + RandomUtil.generateNumber(5))
                    .genderEnum(i%2==0? GenderEnum.MAN:GenderEnum.WOMAN)
                    .build());
        }
    }

    /**
     * 查询指定页的用户
     * @param page 页数
     * @return userList
     */
    public List<User> userList(int page) {
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, 5));
//        return userRepository.findAll();
        return userPage.getContent();
    }

    /**
     * 保存所有用户
     * @param userList 用户列表
     */
    public void saveUserList(List<User> userList) {
        userRepository.saveAll(userList);
    }
}
