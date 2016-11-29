package com.phl.finalize;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2016-08-23.
 * 在根搜索算中不可达的对象，也并非 “非死不可”，这时候它们暂时处于“缓刑”阶段，
 * 要真正宣告一个对象死亡，至少要经历两次标记过程；如果对象在进行根搜索后发现没有与GC ROOT
 * 相连接的引用键，那它将会被第一次标记并且进行一次筛选，筛选的条件是此对象是否有必要执行finalize（）
 * 方法。当对象没有覆盖finalize()方法，或者finalize()方法已经被虚拟机调用过，虚拟机将这两种情况都视
 * 为“没有必要执行”
 * 如果这个对象被判定有必要执行finalize()方法，那么这个对象将会被放置在一个名为F-QUEUE的列队之中，并在稍后
 * 同一条同虚拟机自动建立的，低优先级的Finalizerr线程去执行，这里所谓的执行是虚拟机会触发这个方法，但
 * 并不承诺会等待它运行结束。
 *
 * 稍后GC将对F-QUEUE中的对象进行第二次小规模的标记，如果对象要在 finalize()中完成拯救自己----只要重新
 * 与引用键上的任务一个对象建立关联即可。那在第二次标记时将被移除出“即将回收”的集合
 注：*
 *
 * 任何对象的finalize()方法可会被执行一次，如果对象面临下一你们回收，它的finalize方法将不会执行
 *
 *
 *
 *
 */
public class FinalizeEscapeGc {

    public static FinalizeEscapeGc SAVE_HOOK=null;

    public void isAlive(){
        System.out.println("yes ,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");
        FinalizeEscapeGc.SAVE_HOOK=this;
    }

    public static void main(String[] args) throws  Exception{
        SAVE_HOOK=new FinalizeEscapeGc();
        //对象第一次成功拯救自己
        SAVE_HOOK=null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no ,i am dead");
        }

        //下面这段代码与上面的完全相同，但是这次自救失败
        SAVE_HOOK=null;
        System.gc();
        //因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no ,i am dead");
        }
    }
}
