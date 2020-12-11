package org.geekbang.time.commonmistakes.transaction.nested;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class SubUserService {

    @Autowired
    private UserDataMapper userDataMapper;


    /**
     * Nested的含义：在当前事务中创建一个嵌套事务，如果还没有事务的话，那么就简单的创建一个新的事务；
     * Requires_new的含义：挂起当前的事务，创建一个新的事务，如果还没有事务，那么就简单的创建一个新的事务
     *
     * 首先，requires_new 会创建一个与原事务无关的新事务，尽管是由一个事务调用了另外的一个事务，但是这俩者之间是没有直接的父子关系的。
     * 对于 nested来说，虽然也会创建一个新的事务，但是这个事务与调用者之间是有父子的关系的相互依存的。
     * @param name
     */

    //比较切换为REQUIRES_NEW，这里的createSubUser可以插入数据成功
    @Transactional(propagation = Propagation.NESTED)
//    @Transactional(propagation =  Propagation.REQUIRES_NEW)
    public void createSubUser(String name) {
        userDataMapper.insert(name, "sub");
    }
}
