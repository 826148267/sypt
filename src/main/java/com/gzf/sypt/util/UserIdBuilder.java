package com.gzf.sypt.util;

import com.gzf.sypt.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.Stack;

/**
 * @author guozifan
 * @Description: 创建用户唯一网站ID
 * @date 2018/6/8 17:46
 */
public class UserIdBuilder {
    //时间戳创始时间,2018-06-11 01:13:52
    private static final long PRIMEVAL_TIME = 1528650834372L;

    //定义机器号的上限
    private static final int MAX_MACHINE_IP_NUM = 31;

    //定义序列数位数的上限
    private static final int MAX_SEQUENCE_NUM = 1024;

    //最后一次创建id时的时间戳，用于比对后清空序列化数量
    private static long lastCreateSequenceTime = 0L;

    //定义栈用于存放序列号
    private static Stack<Integer> sequences = new Stack<Integer>();

    @Autowired
    private MachineService machineService;

    /**
     * 借鉴推特用户ID产生方法，可用时间69年左右，时间戳问题
     * 通过当前时间戳减去创始时间戳（41位，毫秒级，不可省）
     * +机器号（7位，容纳108台机器,根据IP划分机器）
     * +毫秒内序列号（10位，上互斥锁，毫秒内容纳1024，再多也容不下，cpu处理能力上升时应该加大，
     *          目前先采用阻塞到1毫秒后重新调用进行处理）
     * 生成userID
     * @return 返回58位用户ID
     */
    public long createUserId() throws UnknownHostException {
        // 获取当前时间的时间戳
        long currentTimeStamps = System.currentTimeMillis();
        // 用于申请用户ID的时间戳，并且关系到序列号的申请
        long timeStamps = currentTimeStamps - PRIMEVAL_TIME;
        // 获取当前机器号，机器号从数据库中查询
        int machineId = machineService.getMachineId();
        // 获取当前秒内的申请序列号
        int sequence = getSequence(timeStamps);
        // 判断是否超出最大机器上限
        if (MAX_MACHINE_IP_NUM < machineId){
            throw new RuntimeException("产生机器号时机器号超出上限，当前机器号为" + machineId
                    + ",最大机器数为" + MAX_MACHINE_IP_NUM);
        }
        System.out.println("时间戳"+timeStamps);
        // 拼接为字符串，转化为long类型返回
        return Long.parseLong(fillStr(Long.toBinaryString(timeStamps), 41, "0")
                + fillStr(Long.toBinaryString(machineId), 7, "0")
                + fillStr(Long.toBinaryString(sequence), 10, "0"), 2);
    }

    /**
     * 根据时间戳获取序列号
     * @param timeStamps
     * @return
     */
    private synchronized int getSequence(long timeStamps){
        Integer sequence = 0; // 返回的序列号
        long sleepTime = 1L; // 阻塞时间
        if (timeStamps == lastCreateSequenceTime) { // 如果当前时间戳与最后一次申请id账号的时间戳相同
            if (sequences.isEmpty() == false) { // 出栈,如果栈不为空
                sequence = sequences.pop(); // 出栈并移除
            }else {     // 如果栈为空
                try {
                    Thread.sleep(sleepTime); // 当前线程睡眠
                    return getSequence(timeStamps + sleepTime); // 阻塞到1毫秒后，回调自身
                } catch (InterruptedException e) {
                    throw new RuntimeException("线程中断异常" + "发生在申请ID中获取序列号时");
                }
            }
        }else if (timeStamps < lastCreateSequenceTime){ // 如果当前时间戳小于最后一次申请时的时间戳
            throw new RuntimeException("创建ID申请序列号时异常：时间戳发生回滚");
        }else { // 如果时间戳大于最后一次申请id账号时的时间戳
            flushSequences();    // 刷新序列号栈（补栈）
            sequence = sequences.pop();  // 出栈
            lastCreateSequenceTime = timeStamps;    // 刷新最后一次申请id账号时的时间戳
        }
        return sequence;
    }

    // 更新栈，补全到最大序列号的大小
    private void flushSequences() {
        int i = MAX_SEQUENCE_NUM - sequences.size(); // 补栈次数
        while (i>0){ // 从最大到最小循环压栈
            sequences.push(i); // 压栈
            i--; // 压栈次数递减
        }
    }

    /**
     *  填充函数
     *  将补足位数的字符串按照相应的位数和指定字符串补足
     *  填充在原字符串前面
     * @param original 原文
     * @param fillNum 填充到多少位数
     * @param filledString 填充的字符串
     * @return 返回填充后的字符串
     */
    private String fillStr(String original, int fillNum, String filledString){
        StringBuilder result = new StringBuilder("");
        int i = fillNum - original.length();
        while (i > 0){
            result.append(filledString);
            i --;
        }
        return result.append(original).toString();
    }
}
