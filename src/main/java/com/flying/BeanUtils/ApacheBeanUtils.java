package com.flying.BeanUtils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝
 * 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
 *
 * apache 的 BeanUtils
 * ApacheBeanUtils
 * 默认情况下，使用org.apache.commons.beanutils.BeanUtils对复杂对象的复制是引用，这是一种浅拷贝
 * 但是由于 Apache下的BeanUtils对象拷贝性能太差，不建议使用，而且在阿里巴巴Java开发规约插件上也明确指出：
 * commons-beantutils 对于对象拷贝加了很多的检验，包括类型的转换，甚至还会检验对象所属的类的可访问性,
 * 可谓相当复杂，这也造就了它的差劲的性能，具体实现代码如下：
 *
 * spring下的BeanUtils也是使用 copyProperties方法进行拷贝，只不过它的实现方式非常简单，就是对两个对象中相同名字的属性进行简单的get/set，
 * 仅检查属性的可访问性。具体实现如下:
 */
public class ApacheBeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        //下面只是用于单独测试
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        System.out.println("personSource: "+personSource);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personDest,personSource);
        System.out.println("persondest: "+personDest);
    }
}
//persondest: PersonDest{id=1, username='pjmike', age=21}
