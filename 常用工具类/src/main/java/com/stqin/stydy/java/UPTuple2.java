package com.stqin.stydy.java;

import java.io.Serializable;

/**
 *
 * @date Mar 14, 2018 4:03:11 PM
 *
 */
public class UPTuple2<T1, T2> implements Serializable {
    private static final long serialVersionUID = 1L;
    public T1 _1;
    public T2 _2;

    public UPTuple2() {}

    public UPTuple2(T1 t1, T2 t2) {
        this._1 = t1;
        this._2 = t2;
    }

    public T1 get_1() {
        return _1;
    }

    public void set_1(T1 t1) {
        this._1 = t1;
    }

    public T2 get_2() {
        return _2;
    }

    public void set_2(T2 t2) {
        this._2 = t2;
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_1 == null) ? 0 : _1.hashCode());
        result = prime * result + ((_2 == null) ? 0 : _2.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UPTuple2<?, ?> other = (UPTuple2<?, ?>) obj;
        if (_1 == null) {
            if (other._1 != null)
                return false;
        } else if (!_1.equals(other._1))
            return false;
        if (_2 == null) {
            if (other._2 != null)
                return false;
        } else if (!_2.equals(other._2))
            return false;
        return true;
    }

//    @Override
//    public int compareTo(Object o) {
//        T1 t1 = this.get_1();
//        T2 t2 = this.get_2();
//        if ((t1 instanceof String) && ( t2 instanceof String)){
//            return ((String) t1).compareTo((String)t2);
//        }
//        return ((Comparable)t1).compareTo(t2);
//    }
}
