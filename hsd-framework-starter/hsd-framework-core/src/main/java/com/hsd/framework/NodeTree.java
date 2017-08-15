package com.hsd.framework;

import com.hsd.framework.util.ReflectUtil;
import com.hsd.framework.util.ValidatorUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class NodeTree<T> {
    private List<T> new_nodes = new ArrayList<>();
    private List<T> nodes;

    private String  attr_id;
    private String  attr_parentId;
    private String  attr_nodes;

    public NodeTree(List<T> nodes,String  _attr_id,String  _attr_parentId,String  _attr_nodes) {
        this.nodes = nodes;
        this.attr_id=_attr_id;
        this.attr_parentId=_attr_parentId;
        this.attr_nodes=_attr_nodes;
    }

    public List<T> buildTree() {
        for (T node : nodes) {
            if (ValidatorUtil.isNullEmpty(ReflectUtil.getValueByFieldName(node, attr_parentId))) {
                new_nodes.add(node);
                build(node);
            }
        }
        return new_nodes;
    }
    private void build(T node) {
        List<T> children = getChildren(node);
        if (!children.isEmpty()) {
            if (ReflectUtil.getValueByFieldName(node, attr_nodes) == null) {
                ReflectUtil.setValueByFieldName(node, attr_nodes, new ArrayList());
            }
            for (T child : children) {
                ((List<T>) ReflectUtil.getValueByFieldName(node, attr_nodes)).add(child);
                build(child);
            }
        }
    }
    private List<T> getChildren(T node) {
        List<T> children = new ArrayList<>();
        Object id = ReflectUtil.getValueByFieldName(node, attr_id);
        for (T child : nodes) {
//            log.info("======"+id+":"+ReflectUtil.getValueByFieldName(child, attr_parentId)+"======");
            if (id.equals(ReflectUtil.getValueByFieldName(child, attr_parentId))) {
                children.add(child);
            }
        }
        return children;
    }
}