package com.flying.xiaomi;

/**
 * 服务之间的接口调用步骤
 * 注册中心
 *
 * 如何保证接口的幂等性？
 * 接口幂等性就是用户对于同一操作发起的一次请求或者多次请求的结果是一致的，不会因为多次点击而产生了副作用。
 * 常见的两种实现方案:  1. 通过代码逻辑判断实现    2. 使用token机制实现
 *
 *  token机制实现步骤:
 *      1. 生成全局唯一的token,token放到redis或jvm内存,token会在页面跳转时获取.存放到pageScope中,支付请求提交先获取token
 *      2. 提交后后台校验token，执行提交逻辑,提交成功同时删除token，生成新的token更新redis ,
 *          这样当第一次提交后token更新了,页面再次提交携带的token是已删除的token后台验证会失败不让提交
 *      token特点：   要申请，一次有效性，可以限流
 *      注意： redis要用删除操作来判断token，删除成功代表token校验通过，如果用select+delete来校验token，存在并发问题，不建议使用
 */
public class Q1 {
}
