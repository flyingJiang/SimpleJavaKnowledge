package com.flying.com.didi.Q6;

/**
 * TABLE:USER
 * id user_code user_name age
 * 求所有重复的user_name，和数量
 * SELECT user_name, count(user_name) count from user group by user_name having count>1
 * select T.CONTRACT_NUMBER, COUNT(T.CONTRACT_NUMBER) CO from T_CONTRACT_REPORT t GROUP BY t.Contract_Number HAVING COUNT(T.CONTRACT_NUMBER)>1;
 */
public class Test {
}
