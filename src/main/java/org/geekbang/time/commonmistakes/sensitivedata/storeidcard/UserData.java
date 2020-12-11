package org.geekbang.time.commonmistakes.sensitivedata.storeidcard;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 对于一些比较敏感的数据
 * 那么需要进行脱敏
 * 加密id
 * 密文
 */
@Data
@Entity
public class UserData {
    @Id
    private Long id;
    private String idcard;//脱敏的身份证
    private Long idcardCipherId;//身份证加密ID
    private String idcardCipherText;//身份证密文
    private String name;//脱敏的姓名
    private Long nameCipherId;//姓名加密ID
    private String nameCipherText;//姓名密文
}
