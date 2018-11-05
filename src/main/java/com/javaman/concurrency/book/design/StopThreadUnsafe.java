package com.javaman.concurrency.book.design;

/**
 * @author pengzhe
 * @date 2018/11/5 23:10
 * @description
 */

public class StopThreadUnsafe {

    public static User user = new User();


    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread changeThread = new ChangeObjectThread();
            changeThread.start();
            Thread.sleep(150);
            ((ChangeObjectThread) changeThread).stopMe();
        }
    }

    public static class ChangeObjectThread extends Thread {

        volatile boolean stopMe = false;

        public void stopMe() {
            stopMe = true;
        }


        @Override
        public void run() {
            while (true) {
                if (stopMe) {
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (user) {
                    int v = (int) System.currentTimeMillis();
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (user) {
                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }


    public static class User {
        private int id;
        private String name;

        public User() {
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}

