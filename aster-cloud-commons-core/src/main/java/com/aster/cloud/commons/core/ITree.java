package com.aster.cloud.commons.core;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * 树型数据结构接口
 * 用于生成树结构，排序等
 *
 * @param <R> 类型必须指定为当前实现的类
 * @author 王骞
 * @date 2020-10-29
 */
public interface ITree<R extends ITree<R>> {

    Logger LOGGER = LoggerFactory.getLogger(ITree.class);

    /**
     * 节点ID
     */
    String getId();

    /**
     * 节点父ID
     */
    String getPid();

    /**
     * 排序字段
     */
    Integer getSort();

    /**
     * children字段
     *
     * @return List
     */
    List<R> getChildren();

    /**
     * set children字段
     *
     * @param list
     */
    void setChildren(List<R> list);

    default List<R> childrenNotNull() {
        List<R> children = getChildren();
        if (children == null) {
            setChildren(new LinkedList<>());
        }
        return getChildren();
    }

    /**
     * 先创造一个root实例, 再调用此方法生成树结构
     *
     * @param dataList 原始List
     * @param function T为dataList元素, R为最终返回值, 即实现ITree的实例
     * @param <T>      dataList元素
     * @return this
     */
    default <T> R wrapRoot(List<T> dataList, Function<T, R> function) {
        HashMap<String, R> hashMap = MapUtil.newHashMap();
        dataList.forEach(t -> {
            R node = function.apply(t);
            hashMap.put(node.getId(), node);
        });

        hashMap.forEach((k, v) -> {
            String parentId = v.getPid();
            if (StrUtil.isNotBlank(parentId)) {
                R parenNode = hashMap.get(parentId);
                if (parenNode == null) {
                    LOGGER.error("树结构节点pid数据错误, pid:" + parentId);
                    // throw new NullPointerException("树结构节点pid数据错误");
                    return;
                }
                parenNode.childrenNotNull().add(v);
                return;
            }
            // root add children
            this.childrenNotNull().add(v);
        });
        return (R) this;
    }

    /**
     * 触发排序
     *
     * @return this
     */
    default R sort() {
        if (childrenNotNull().isEmpty()) {
            return (R) this;
        }
        childrenNotNull().forEach(ITree::sort);
        childrenNotNull().sort(Comparator.comparingInt(ITree::getSort));
        return (R) this;
    }

}
