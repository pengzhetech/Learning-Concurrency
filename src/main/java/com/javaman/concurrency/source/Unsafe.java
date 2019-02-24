//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.javaman.concurrency.source;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;

import sun.misc.VM;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public final class Unsafe {
    private static final Unsafe theUnsafe;
    public static final int INVALID_FIELD_OFFSET = -1;
    public static final int ARRAY_BOOLEAN_BASE_OFFSET;
    public static final int ARRAY_BYTE_BASE_OFFSET;
    public static final int ARRAY_SHORT_BASE_OFFSET;
    public static final int ARRAY_CHAR_BASE_OFFSET;
    public static final int ARRAY_INT_BASE_OFFSET;
    public static final int ARRAY_LONG_BASE_OFFSET;
    public static final int ARRAY_FLOAT_BASE_OFFSET;
    public static final int ARRAY_DOUBLE_BASE_OFFSET;
    public static final int ARRAY_OBJECT_BASE_OFFSET;
    public static final int ARRAY_BOOLEAN_INDEX_SCALE;
    public static final int ARRAY_BYTE_INDEX_SCALE;
    public static final int ARRAY_SHORT_INDEX_SCALE;
    public static final int ARRAY_CHAR_INDEX_SCALE;
    public static final int ARRAY_INT_INDEX_SCALE;
    public static final int ARRAY_LONG_INDEX_SCALE;
    public static final int ARRAY_FLOAT_INDEX_SCALE;
    public static final int ARRAY_DOUBLE_INDEX_SCALE;
    public static final int ARRAY_OBJECT_INDEX_SCALE;
    public static final int ADDRESS_SIZE;

    private static native void registerNatives();

    private Unsafe() {
    }

    @CallerSensitive
    public static Unsafe getUnsafe() {
        Class var0 = Reflection.getCallerClass();
        if (!VM.isSystemDomainLoader(var0.getClassLoader())) {
            throw new SecurityException("Unsafe");
        } else {
            return theUnsafe;
        }
    }

    //获取给定对象偏移量上的int值,所谓的偏移量可以简单理解为指针指向该偏移量的内存地址
    //通过偏移量便可得到该对象的变量,进行各种操作
    public native int getInt(Object var1, long var2);

    //设定给定对象上偏移量的int值
    public native void putInt(Object var1, long var2, int var4);

    //获取给定对象偏移量上的引用类型的值
    public native Object getObject(Object var1, long var2);

    //设定给定对象偏移量上的引用类型的值
    public native void putObject(Object var1, long var2, Object var4);

    public native boolean getBoolean(Object var1, long var2);

    public native void putBoolean(Object var1, long var2, boolean var4);

    public native byte getByte(Object var1, long var2);

    public native void putByte(Object var1, long var2, byte var4);

    public native short getShort(Object var1, long var2);

    public native void putShort(Object var1, long var2, short var4);

    public native char getChar(Object var1, long var2);

    public native void putChar(Object var1, long var2, char var4);

    public native long getLong(Object var1, long var2);

    public native void putLong(Object var1, long var2, long var4);

    public native float getFloat(Object var1, long var2);

    public native void putFloat(Object var1, long var2, float var4);

    public native double getDouble(Object var1, long var2);

    public native void putDouble(Object var1, long var2, double var4);

    /**
     * @deprecated
     */
    @Deprecated
    public int getInt(Object var1, int var2) {
        return this.getInt(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putInt(Object var1, int var2, int var3) {
        this.putInt(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Object getObject(Object var1, int var2) {
        return this.getObject(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putObject(Object var1, int var2, Object var3) {
        this.putObject(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public boolean getBoolean(Object var1, int var2) {
        return this.getBoolean(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putBoolean(Object var1, int var2, boolean var3) {
        this.putBoolean(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public byte getByte(Object var1, int var2) {
        return this.getByte(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putByte(Object var1, int var2, byte var3) {
        this.putByte(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public short getShort(Object var1, int var2) {
        return this.getShort(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putShort(Object var1, int var2, short var3) {
        this.putShort(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public char getChar(Object var1, int var2) {
        return this.getChar(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putChar(Object var1, int var2, char var3) {
        this.putChar(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public long getLong(Object var1, int var2) {
        return this.getLong(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putLong(Object var1, int var2, long var3) {
        this.putLong(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public float getFloat(Object var1, int var2) {
        return this.getFloat(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putFloat(Object var1, int var2, float var3) {
        this.putFloat(var1, (long) var2, var3);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public double getDouble(Object var1, int var2) {
        return this.getDouble(var1, (long) var2);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public void putDouble(Object var1, int var2, double var3) {
        this.putDouble(var1, (long) var2, var3);
    }



    /*--------------------------内存相关方法------------------------------------*/

    //获取给定内存地址var1的byte值
    public native byte getByte(long var1);

    //设定给定内存地址var1的byte值
    public native void putByte(long var1, byte var3);

    public native short getShort(long var1);

    public native void putShort(long var1, short var3);

    public native char getChar(long var1);

    public native void putChar(long var1, char var3);

    public native int getInt(long var1);

    public native void putInt(long var1, int var3);

    public native long getLong(long var1);

    public native void putLong(long var1, long var3);

    public native float getFloat(long var1);

    public native void putFloat(long var1, float var3);

    public native double getDouble(long var1);

    public native void putDouble(long var1, double var3);

    //获取给定地址的内存值
    public native long getAddress(long var1);

    //设置给定地址var1的内存值
    public native void putAddress(long var1, long var3);


    //分配指定大小var1的内存
    public native long allocateMemory(long var1);

    //根据给定的内存地址var1重新分配指定大小的内存var3
    public native long reallocateMemory(long var1, long var3);

    //将指定对象的给定offset偏移量内存块中的所有字节设置固定值
    public native void setMemory(Object var1, long var2, long var4, byte var6);

    public void setMemory(long var1, long var3, byte var5) {
        this.setMemory((Object) null, var1, var3, var5);
    }

    public native void copyMemory(Object var1, long var2, Object var4, long var5, long var7);

    public void copyMemory(long var1, long var3, long var5) {
        this.copyMemory((Object) null, var1, (Object) null, var3, var5);
    }

    //用于释放allocateMemory和reallocateMemory申请的内存
    public native void freeMemory(long var1);

    //操作系统的内存页大小
    public native int pageSize();

    /**
     * @deprecated
     */
    @Deprecated
    public int fieldOffset(Field var1) {
        return Modifier.isStatic(var1.getModifiers()) ? (int) this.staticFieldOffset(var1) : (int) this.objectFieldOffset(var1);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public Object staticFieldBase(Class<?> var1) {
        Field[] var2 = var1.getDeclaredFields();

        for (int var3 = 0; var3 < var2.length; ++var3) {
            if (Modifier.isStatic(var2[var3].getModifiers())) {
                return this.staticFieldBase(var2[var3]);
            }
        }

        return null;
    }

    /*------------------类和实例对象及变量的操作------------------*/

    /**
     * 静态属性的偏移量,用于在对应的Class对象中读写静态属性
     *
     * @param var1
     * @return
     */
    public native long staticFieldOffset(Field var1);

    /**
     * 获取字段在实例中的偏移量
     *
     * @param var1
     * @return
     */
    public native long objectFieldOffset(Field var1);

    public native Object staticFieldBase(Field var1);

    //判断是否需要加载一个类
    public native boolean shouldBeInitialized(Class<?> var1);

    //确保类一定被加载
    public native void ensureClassInitialized(Class<?> var1);

    /*--------------------------数组操作---------------------------*/
    //获取数组第一个元素的偏移地址
    public native int arrayBaseOffset(Class<?> var1);

    //数组中一个元素占据的内存你空间,arrayBaseOffset与arrayIndexScale配合使用,可定位数组中每个元素在内存中的位置
    public native int arrayIndexScale(Class<?> var1);

    public native int addressSize();


    //告诉虚拟机定义了一个没有安全检查的类,默认情况下这个类加载器和保护域来着调用者类
    public native Class<?> defineClass(String var1, byte[] var2, int var3, int var4, ClassLoader var5, ProtectionDomain var6);

    //加载一个匿名类
    public native Class<?> defineAnonymousClass(Class<?> var1, byte[] var2, Object[] var3);


    /*提供实例对象新途径*/

    /**
     * 传入一个对象的class并创建给实例对象,但是并不会调用构造方法
     *
     * @param var1
     * @return
     * @throws InstantiationException
     */
    public native Object allocateInstance(Class<?> var1) throws InstantiationException;

    /*--------------------锁操作---------------------*/

    /**
     * @deprecated
     */
    @Deprecated
    public native void monitorEnter(Object var1);

    /**
     * @deprecated
     */
    @Deprecated
    public native void monitorExit(Object var1);

    /**
     * @deprecated
     */
    @Deprecated
    public native boolean tryMonitorEnter(Object var1);

    /*--------------------锁操作---------------------*/

    public native void throwException(Throwable var1);

    /*--------------------------------------------CAS相关操作--------------------------------------------*/

    /**
     * @param obj    给定对象
     * @param offset 对象内存的偏移量,通过这个偏移量迅速定位字段设置或获取该字段的值
     * @param expect 期望值
     * @param update 要设置的值
     * @return
     */
    public final native boolean compareAndSwapObject(Object obj, long offset, Object expect, Object update);

    /**
     * @param obj    需要更新的对象obj(操作的上下文对象)
     * @param offset obj中整形field的偏移量(待修改成员地址偏移量)
     * @param expect 希望field中存在的值(修改成员变量的期望值)
     * @param update 如果期望值expect与field中的当前值相同,设置field的值为这个新值(如果期望值和待修改的成员的实际值一致
     *               ,则将待修改成员的值(内存值)修改为update)
     * @return 如果field的值被更改返回true
     * 从参数表述可以看出,每次修改变量值之前都会比较当前实际值和预期值是否一致,只有一致才会执行修改,否则do nothing
     * cas的原子性依靠硬件保证
     */
    public final native boolean compareAndSwapInt(Object obj, long offset, int expect, int update);

    public final native boolean compareAndSwapLong(Object obj, long offset, long expect, long update);

    /*--------------------------------------------CAS相关操作--------------------------------------------*/
    public native Object getObjectVolatile(Object var1, long var2);

    public native void putObjectVolatile(Object var1, long var2, Object var4);

    //获得给定对象的指定偏移量var2的int值,使用volatile语义,总能获取到最新的int值
    public native int getIntVolatile(Object var1, long var2);

    //设置给定对象的int值,使用volatile语义,即设置后立马更新到内存对其他线程可见
    public native void putIntVolatile(Object var1, long var2, int var4);

    public native boolean getBooleanVolatile(Object var1, long var2);

    public native void putBooleanVolatile(Object var1, long var2, boolean var4);

    public native byte getByteVolatile(Object var1, long var2);

    public native void putByteVolatile(Object var1, long var2, byte var4);

    public native short getShortVolatile(Object var1, long var2);

    public native void putShortVolatile(Object var1, long var2, short var4);

    public native char getCharVolatile(Object var1, long var2);

    public native void putCharVolatile(Object var1, long var2, char var4);

    public native long getLongVolatile(Object var1, long var2);

    public native void putLongVolatile(Object var1, long var2, long var4);

    public native float getFloatVolatile(Object var1, long var2);

    public native void putFloatVolatile(Object var1, long var2, float var4);

    public native double getDoubleVolatile(Object var1, long var2);

    public native void putDoubleVolatile(Object var1, long var2, double var4);

    public native void putOrderedObject(Object var1, long var2, Object var4);

    //与putIntVolatile一样,但要求被操作字段必须有volatile修饰
    public native void putOrderedInt(Object var1, long var2, int var4);

    public native void putOrderedLong(Object var1, long var2, long var4);

    /*------------------------挂起和恢复----------------------------------*/

    /**
     * 将一个线程进行挂起是通过park方法实现的,调用park后,线程将一直阻塞知道超时或者中断条件出现,unpark可以终止一个挂起的线程
     * 使其回复正常,java对线程的挂起操作被封装在LockSupport类中,LockSupport中有各种版本的park方法,其底层实现还是要使用
     * Unsafe.park方法和unsafe.unpark方法
     */
    public native void unpark(Object var1);

    public native void park(boolean var1, long var2);

    public native int getLoadAverage(double[] var1, int var2);


    /*-----------------------------1.8新增,基于CAS实现-------------------------------------*/

    /**
     * 1.81新增,对于给定对象var1,根据获取内存地址偏移量指向的字段,将其增加var4
     * 这是一个CAS操作过程,直到设置成功方能退出循环,返回旧值
     *
     * @param var1
     * @param var2
     * @param var4
     * @return
     */
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            //获取内存中最新值
            var5 = this.getIntVolatile(var1, var2);
            //通过CAS操作
        } while (!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }

    public final long getAndAddLong(Object var1, long var2, long var4) {
        long var6;
        do {
            var6 = this.getLongVolatile(var1, var2);
        } while (!this.compareAndSwapLong(var1, var2, var6, var6 + var4));

        return var6;
    }

    public final int getAndSetInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while (!this.compareAndSwapInt(var1, var2, var5, var4));

        return var5;
    }

    public final long getAndSetLong(Object var1, long var2, long var4) {
        long var6;
        do {
            var6 = this.getLongVolatile(var1, var2);
        } while (!this.compareAndSwapLong(var1, var2, var6, var4));

        return var6;
    }

    public final Object getAndSetObject(Object var1, long var2, Object var4) {
        Object var5;
        do {
            var5 = this.getObjectVolatile(var1, var2);
        } while (!this.compareAndSwapObject(var1, var2, var5, var4));

        return var5;
    }

    /*-------------------------------内存屏障操作--------------------------------*/
    //在该方法之前的所有读操作,一定在load屏障之前能完成
    public native void loadFence();

    //在该方法之前的所有写操作,一定在store屏障之前完成
    public native void storeFence();

    //在该方法之前的所有读写操作,一定在full屏障之前执行完成,这个内存屏障相当于上面两个的合体
    public native void fullFence();

    private static void throwIllegalAccessError() {
        throw new IllegalAccessError();
    }

    static {
        registerNatives();
        Reflection.registerMethodsToFilter(Unsafe.class, new String[]{"getUnsafe"});
        theUnsafe = new Unsafe();
        ARRAY_BOOLEAN_BASE_OFFSET = theUnsafe.arrayBaseOffset(boolean[].class);
        ARRAY_BYTE_BASE_OFFSET = theUnsafe.arrayBaseOffset(byte[].class);
        ARRAY_SHORT_BASE_OFFSET = theUnsafe.arrayBaseOffset(short[].class);
        ARRAY_CHAR_BASE_OFFSET = theUnsafe.arrayBaseOffset(char[].class);
        ARRAY_INT_BASE_OFFSET = theUnsafe.arrayBaseOffset(int[].class);
        ARRAY_LONG_BASE_OFFSET = theUnsafe.arrayBaseOffset(long[].class);
        ARRAY_FLOAT_BASE_OFFSET = theUnsafe.arrayBaseOffset(float[].class);
        ARRAY_DOUBLE_BASE_OFFSET = theUnsafe.arrayBaseOffset(double[].class);
        ARRAY_OBJECT_BASE_OFFSET = theUnsafe.arrayBaseOffset(Object[].class);
        ARRAY_BOOLEAN_INDEX_SCALE = theUnsafe.arrayIndexScale(boolean[].class);
        ARRAY_BYTE_INDEX_SCALE = theUnsafe.arrayIndexScale(byte[].class);
        ARRAY_SHORT_INDEX_SCALE = theUnsafe.arrayIndexScale(short[].class);
        ARRAY_CHAR_INDEX_SCALE = theUnsafe.arrayIndexScale(char[].class);
        ARRAY_INT_INDEX_SCALE = theUnsafe.arrayIndexScale(int[].class);
        ARRAY_LONG_INDEX_SCALE = theUnsafe.arrayIndexScale(long[].class);
        ARRAY_FLOAT_INDEX_SCALE = theUnsafe.arrayIndexScale(float[].class);
        ARRAY_DOUBLE_INDEX_SCALE = theUnsafe.arrayIndexScale(double[].class);
        ARRAY_OBJECT_INDEX_SCALE = theUnsafe.arrayIndexScale(Object[].class);
        ADDRESS_SIZE = theUnsafe.addressSize();
    }
}
