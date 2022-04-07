package com.civitasv.spider.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.civitasv.spider.helper.OutputType;
import com.civitasv.spider.helper.TaskStatus;
import com.civitasv.spider.helper.UserType;
import com.civitasv.spider.model.bo.Task;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghang
 * @since 2022-04-06 09:08:52
 */
@TableName("task")
public class TaskPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("keys")
    private String keys;

    @TableField("types")
    private String types;

    @TableField("keywords")
    private String keywords;

    @TableField("threadnum")
    private Integer threadnum;

    @TableField("threshold")
    private Integer threshold;

    @TableField("user_type")
    private Integer userType;

    @TableField("output_directory")
    private String outputDirectory;

    @TableField("output_type")
    private Integer outputType;

    @TableField("request_actual_times")
    private Integer requestActualTimes;

    @TableField("request_excepted_times")
    private Integer requestExceptedTimes;

    @TableField("poi_actial_sum")
    private Integer poiActialSum;

    @TableField("poi_excetued_sum")
    private Integer poiExcetuedSum;

    @TableField("total_executed_time")
    private Integer totalExecutedTime;

    @TableField("status")
    private Integer status;

    @TableField("bound_config")
    private String boundConfig;

    @TableField("bounds")
    private String bounds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getThreadnum() {
        return threadnum;
    }

    public void setThreadnum(Integer threadnum) {
        this.threadnum = threadnum;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public Integer getOutputType() {
        return outputType;
    }

    public void setOutputType(Integer outputType) {
        this.outputType = outputType;
    }

    public Integer getRequestActualTimes() {
        return requestActualTimes;
    }

    public void setRequestActualTimes(Integer requestActualTimes) {
        this.requestActualTimes = requestActualTimes;
    }

    public Integer getRequestExceptedTimes() {
        return requestExceptedTimes;
    }

    public void setRequestExceptedTimes(Integer requestExceptedTimes) {
        this.requestExceptedTimes = requestExceptedTimes;
    }

    public Integer getPoiActialSum() {
        return poiActialSum;
    }

    public void setPoiActialSum(Integer poiActialSum) {
        this.poiActialSum = poiActialSum;
    }

    public Integer getPoiExcetuedSum() {
        return poiExcetuedSum;
    }

    public void setPoiExcetuedSum(Integer poiExcetuedSum) {
        this.poiExcetuedSum = poiExcetuedSum;
    }

    public Integer getTotalExecutedTime() {
        return totalExecutedTime;
    }

    public void setTotalExecutedTime(Integer totalExecutedTime) {
        this.totalExecutedTime = totalExecutedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBoundConfig() {
        return boundConfig;
    }

    public void setBoundConfig(String boundConfig) {
        this.boundConfig = boundConfig;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", keys=" + keys +
                ", types=" + types +
                ", keywords=" + keywords +
                ", threadnum=" + threadnum +
                ", threshold=" + threshold +
                ", userType=" + userType +
                ", outputDirectory=" + outputDirectory +
                ", outputType=" + outputType +
                ", requestActualTimes=" + requestActualTimes +
                ", requestExceptedTimes=" + requestExceptedTimes +
                ", poiActialSum=" + poiActialSum +
                ", poiExcetuedSum=" + poiExcetuedSum +
                ", totalExecutedTime=" + totalExecutedTime +
                ", status=" + status +
                ", boundConfig=" + boundConfig +
                "}";
    }

    public Task toTask(){
        Queue<String> queue = Arrays.stream(keys.split(",")).collect(Collectors.toCollection(LinkedList::new));
        try {
           return new Task(id, queue, types, keywords, threadnum, threshold,outputDirectory,
                   OutputType.getOutputType(outputType), UserType.getUserType(userType), requestActualTimes,
                    requestExceptedTimes, poiActialSum, poiExcetuedSum, totalExecutedTime, boundConfig,
                   TaskStatus.getBoundryType(status),
                   Arrays.stream(bounds.split("\\|")).map(Double::valueOf).toArray(Double[]::new));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}