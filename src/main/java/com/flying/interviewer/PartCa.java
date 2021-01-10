package com.flying.interviewer;

public class PartCa {
}

/*
分科目统计每科前三名的学生的语句
有个成绩表 score（student_no,Subject_no,Score）分别为学号，课程号，成绩。我想用语句查询出每科的前三名学生的学号
https://blog.csdn.net/adayan_2015/article/details/81080586

        MySQL

        select  subject_id,student_id,score
        from score sc1
        where  (select count(1) from score sc2 where sc2.subject_id=sc1.subject_id and sc2.score >= sc1.score) <=3
        order by subject_id ,score desc;

        https://blog.csdn.net/qq_36770189/article/details/99674117?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.nonecase

        利用hiveSQL实现：
        select * from
        (select name, subject,
        rank() over(partition by subject order by score desc) t
        from score) tt
        where tt.t<4;

        利用窗口函数对按各学科分区，并降序排列，利用rank（）函数规避有相同成绩名次的不一的情况学生。最后where语句取出前三名。
        rank（）排序相同时会重复，总数不变
        dense_rank()排序相同时会重复，总数会减少
        row_rank()会顺序计算

        https://www.cnblogs.com/anyhow/p/3641681.html

        ORACLE分科目统计每科前三名的学生的语句
        有个成绩表 score（student_no,Subject_no,Score）分别为学号，课程号，成绩。我想用语句查询出每科的前三名学生的学号，请各位高手教教小弟
        select * from (
        select  t.*,row_number() over(partition by t.subject_no order by t.score desc) pm from score t) where pm<4;


        https://www.cnblogs.com/myseries/p/11265849.html
*/
