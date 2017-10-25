package com.mandy.testGuava.other;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * test the com.google.common.base.Function
 * Function: (input) output
 *  apply方法
 *  Functions 工厂方法
 *      ToStringFunctions 枚举的单例模式，final 不能被继承，天生懒加载的 input checkNotNull toString
 *
 * Predicate:(input) boolean:
 * Supplier: ()output
 * Functional Program 函数式编程，强调函数达到目标，而不是改变其状态
 */
public class TestFunction {

    //Function 继承了java8中的Function
    public static void main(String[] args) throws IOException {
        Function function=new Function<String,Integer>(){
            public Integer apply(String input) {
                Preconditions.checkNotNull(input,"The input should be not null");
                return input.length();
            }
        };
        System.out.println(function.apply("abc"));
        //策略模式 演化过来：由调用方制定具体的调用方法
        process("abc",new Handler.StringHandler());
        System.out.println(Functions.toStringFunction().apply(new ServerSocket(8888)));

    }

    interface Handler<IN,OUT>{
        OUT handler(IN input);

        class StringHandler implements Handler<String,Integer>{

            public Integer handler(String input) {
                Preconditions.checkNotNull(input,"The input should be not null");
                return input.length();
            }
        }
    };


    private static void process(String str,Handler<String,Integer> handler){
        System.out.println(handler.handler(str));
    }
}
