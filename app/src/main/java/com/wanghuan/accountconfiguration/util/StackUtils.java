package com.wanghuan.accountconfiguration.util;

import android.content.Context;
import android.text.TextUtils;

/**
 * Created by h1 on 16/3/11 16:42.
 * email: h18501667737@gmail.com
 */
public class StackUtils {

    private final static String STACK_TRACE_ELEMENT_NULL = "[ stacktrace elements is null ]";

    /**
     * current method name
     * @return
     */
    public static String getMethodName(Context context){
        StackTraceElement[] stackTraceElements = getStackTraceElements();
        if(stackTraceElements != null && stackTraceElements.length > 0){
            for (StackTraceElement element: stackTraceElements) {
                String className = element.getClassName();
                if(!TextUtils.isEmpty(className) && className.contains(context.getClass().getSimpleName())){
                    return element.getMethodName();
                }
            }
        }
        return STACK_TRACE_ELEMENT_NULL;
    }

    /**
     *
     * @return
     */
    private static StackTraceElement[] getStackTraceElements(){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements;
    }

    /**
     *
     */
    public static void listStackTraceElements(){
        StackTraceElement[] stackTraceElements = getStackTraceElements();
        if(stackTraceElements != null){
            for (int i = 0; i < stackTraceElements.length; i++){
                L.d(stackTraceElements[i].getClassName() + " "
                        + stackTraceElements[i].getMethodName() + " "
                        + stackTraceElements[i].getFileName() + " "
                        + stackTraceElements[i].getLineNumber());
            }
        }
    }
}
