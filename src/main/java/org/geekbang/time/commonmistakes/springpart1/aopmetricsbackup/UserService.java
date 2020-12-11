package org.geekbang.time.commonmistakes.springpart1.aopmetricsbackup;

import lombok.extern.slf4j.Slf4j;
import org.geekbang.time.commonmistakes.transaction.transactionpropagation.UserEntity;
import org.geekbang.time.commonmistakes.transaction.transactionpropagation.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Legal
 * @date 2020/9/22
 */

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //启用监控
    @Transactional
    @Metrics
    public void createUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username");
        }
    }

    public int getUserCount(String name) {
        return userRepository.findByName(name).size();
    }
}
