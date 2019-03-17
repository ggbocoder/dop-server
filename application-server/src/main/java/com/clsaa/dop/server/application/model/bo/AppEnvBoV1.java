package com.clsaa.dop.server.application.model.bo;

import com.clsaa.dop.server.application.model.po.AppEnvironment;
import lombok.*;

import javax.persistence.Column;


/**
 * 应用环境业务层对象
 *
 * @author Bowen
 * @since 2019-3-14
 **/
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppEnvBoV1 {

    private Long id;

    /**
     * 环境名称
     */
    private String title;

    /**
     * 目标集群
     */
    private String targetCluster;

    /**
     * 命名空间
     */
    private String nameSpace;

    /**
     * 对应服务
     */
    private String service;

    /**
     * 发布批次
     */
    private Integer releaseBatch;


    /**
     * 发布策略
     */
    private AppEnvironment.ReleaseStrategy releaseStrategy;

    /**
     * 部署策略
     */
    private AppEnvironment.DeploymentStrategy deploymentStrategy;


    /**
     * 环境级别
     */
    private AppEnvironment.EnvironmentLevel environmentLevel;

}