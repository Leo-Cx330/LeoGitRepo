package com.leo.app.utils;



import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ExtHashMap<K,V>  {

    Node<K,V>[] table;
    static final  int   DEFAULT_INITIAL_CAPACITY=1 << 4; //默认初始容量
    static final  float DEFAULT_LOAD_FACTOR = 0.75f; //负载因子
    public void put(K key,V value){
        //1.校验key对应的数组是否存在
        int hash = key.hashCode();
        if(table==null || table.length==0){
            int capMap= (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
            table=new Node[capMap];
            Node node=new Node(hash,key,value,null);
            int index = hash % table.length;
            table[index]=node;
        }else{
            for (Node node:table) {
                if(node.key.equals(key) || node.key.hashCode()==key.hashCode()){
                    node.value=value;//覆盖
                }else{
                    Node next = node.next;
                    while(next!=null){
                        if(next.key.hashCode()==key.hashCode()||next.key.equals(key)){

                        }
                    }
                }
            }
        }



    }



    static class Node<K,V> implements Map.Entry<K,V> {

        final int hash;
        final K key;
        V value;
        ExtHashMap.Node<K,V> next;

        Node(int hash, K key, V value, ExtHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }

    }

}
