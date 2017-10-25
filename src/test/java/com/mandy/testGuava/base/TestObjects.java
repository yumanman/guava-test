package com.mandy.testGuava.base;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

/**
 * test the com.google.common.base.Objects
 * hashCode
 * equals
 * toString
 * compareTo
 */
public class TestObjects {

    static class Guava implements Comparable<Guava>{
        private final String name;
        private final String value;

        public Guava(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .omitNullValues()
                    .add("name",this.name)
                    .add("value",this.value).toString();

        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name,value);
        }

        @Override
        public boolean equals(Object obj) {
            if(this==obj)return true;
            if (obj==null||getClass()!=obj.getClass())return false;
            Guava guava= (Guava) obj;
            return Objects.equal(this.name,guava.name)&&
                    Objects.equal(this.value,guava.value);
        }


        public int compareTo(Guava o) {
            return ComparisonChain.start().compare(this.name,o.name)
                    .compare(this.value,o.value).result();
        }
    }

    public static void main(String[] args) {
        Guava guava=new Guava("name1","values");
        System.out.println(guava);
        System.out.println(guava.hashCode());
        Guava guava1=new Guava("name2","values");
        System.out.println(guava.compareTo(guava1));
    }
}
