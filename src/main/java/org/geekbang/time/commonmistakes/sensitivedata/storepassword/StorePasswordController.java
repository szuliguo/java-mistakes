package org.geekbang.time.commonmistakes.sensitivedata.storepassword;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * md5 加密和 BCrypt 的区别
 * https://draveness.me/whys-the-design-password-with-md5/
 * md5 其实不算是一种真正的加密算法
 * 只能算是一种 hash 算法
 * 因此会存在着 hash 碰撞的危险
 * 这也是 彩虹表存在的原因
 */
@RestController
@Slf4j
@RequestMapping("storepassword")
public class StorePasswordController {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    /**
     * 存储密码的时候不能直接md5 加密密码就可以了。
     * 使用字典彩虹表就可以破解了
     * @param name
     * @param password
     * @return
     */
    @GetMapping("wrong1")
    public UserData wrong1(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setPassword(DigestUtils.md5Hex(password));
        return userRepository.save(userData);
    }

    /**
     * 需要加salt,但是如果每个用户的salt都是一样的话，
     * 就会储出错
     * @param name
     * @param password
     * @return
     */
    @GetMapping("wrong2")
    public UserData wrong2(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setPassword(DigestUtils.md5Hex("salt" + password));
        return userRepository.save(userData);
    }

    /**
     * 通常不实用 用户的属性作为 salt
     * @param name
     * @param password
     * @return
     */
    @GetMapping("wrong3")
    public UserData wrong3(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setPassword(DigestUtils.md5Hex(name + password));
        return userRepository.save(userData);
    }

    /**
     * 使用 md5 进行俩次加密也是不可行的
     * @param name
     * @param password
     * @return
     */
    @GetMapping("wrong4")
    public UserData wrong4(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(password)));
        return userRepository.save(userData);
    }

    /**
     * 每个用户的salt，是独一无二的。
     * 并且需要存储下来
     * @param name
     * @param password
     * @return
     */
    @GetMapping("right")
    public UserData right(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setSalt(UUID.randomUUID().toString());
        userData.setPassword(DigestUtils.md5Hex(userData.getSalt() + password));
        return userRepository.save(userData);
    }

    /**
     * 更好的做法是使用 BCryptPasswordEncoder 来进行加密就可以了
     * @param name
     * @param password
     * @return
     */
    @GetMapping("better")
    public UserData better(@RequestParam(value = "name", defaultValue = "朱晔") String name, @RequestParam(value = "password", defaultValue = "Abcd1234") String password) {
        UserData userData = new UserData();
        userData.setId(1L);
        userData.setName(name);
        userData.setPassword(passwordEncoder.encode(password));
        userRepository.save(userData);
        log.info("match ? {}", passwordEncoder.matches(password, userData.getPassword()));
        return userData;
    }


    /**
     * 比较 md5 加密 和 BCrypt 加密的效率
     * md5 加密是摘要，效率会快一点
     * BCrypt 加密效率会慢一点
     * 所以 如果是使用 BCrypt 进行加密的话，
     * 那么生成20位的密码需要几十年
     * 代价太大了
     * 所以可以使用md5进行加密。
     */
    @GetMapping("performance")
    public void performance() {
        StopWatch stopWatch = new StopWatch();
        String password = "Abcd1234";
        stopWatch.start("MD5");
        DigestUtils.md5Hex(password);
        stopWatch.stop();
        stopWatch.start("BCrypt(10)");
        String hash1 = BCrypt.gensalt(10);
        BCrypt.hashpw(password, hash1);
        System.out.println(hash1);
        stopWatch.stop();
        stopWatch.start("BCrypt(12)");
        String hash2 = BCrypt.gensalt(12);
        BCrypt.hashpw(password, hash2);
        System.out.println(hash2);
        stopWatch.stop();
        stopWatch.start("BCrypt(14)");
        String hash3 = BCrypt.gensalt(14);
        BCrypt.hashpw(password, hash3);
        System.out.println(hash3);
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());
    }

}
