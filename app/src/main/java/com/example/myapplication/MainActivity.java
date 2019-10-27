package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import butterknife.BindView;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends Activity {

    private Lock lock;
    private Condition fullCondition, emptyCondition;
    private final int MAX = 10;
    private volatile int count;
    @BindView(R.id.codeButton)
    private TextView codeButton;
    CodeAsyncTask codeAsyncTask;
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    long time;
    Disposable disposable;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int[] a = new int[]{8, 4, 6, 5, 7, 0, 1, 2};
//        maoSort(a);
        quickSort(a, 0, a.length - 1);
//        selectSort(a);
//        insertSort(a);
        binarySearch(a, 0, a.length, 5);
        printArray(a);
    }

    void printArray(int[] a) {
        String str = "";
        for (int i = 0; i < a.length; i++) {
            str += a[i] + " ";
        }
        Log.e("code111", str);
    }

    private int binarySearch(int[] a, int left, int right, int target) {
        int mid = left + (right - left) / 2;
        if (target < a[mid]) {
            return binarySearch(a, left, mid - 1, target);
        } else if (target > a[mid]) {
            return binarySearch(a, mid + 1, right, target);
        } else {
            return mid;
        }
    }

    private void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    private void insertSort(int[] a) {
        int temp;
        int j;
        for (int i = 1; i < a.length; i++) {
            temp = a[i];
            for (j = i - 1; j >= 0; j--) {
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;
        }
    }

    private void maoSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
    }

    private void quickSort(int[] a, int left, int right) {
        if (left >= right) return;
        int i = left;
        int j = right;
        int temp = a[i];
        while (i < j) {
            while (i < j && temp <= a[j]) {
                j--;
            }
            if (i < j)
                a[i++] = a[j];
            while (i < j && temp >= a[i]) {
                i++;
            }
            if (i < j)
                a[j--] = a[i];
        }
        a[i] = temp;
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    class Work implements Runnable {
        WorkCallBack workCallBack;

        public Work(WorkCallBack callback) {
            workCallBack = callback;
        }

        @Override
        public void run() {
            workCallBack.error();
            ;
        }
    }

    interface WorkCallBack {
        void finish();

        void error();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    class Person {
        private int id;
        private String name;
        private int age;

        public Person(int i, String s, int i1) {
            this.id = i;
            this.name = s;
            this.age = i1;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public void resume(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(intent);
        Bundle bundle = new Bundle();
        Map map = new HashMap();
        map.put(1, 2);
        map.put(3, 4);
        map.put(100, 4);
//        codeAsyncTask.execute();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        ExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(9);
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private class Producter implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (count == MAX) {
                    try {
                        fullCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
                Log.e("code111", "make 1,total:" + count);
                emptyCondition.signal();
                lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class CodeAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... strings) {
            return "1";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

    private class Customer implements Runnable {
        String mName;

        public Customer(String name) {
            this.mName = name;
        }

        @CodeRetention
        public void add() {
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (count == 0) {
                    try {
                        emptyCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count--;
                Log.e("code111", "sale 1 by" + mName + ",total:" + count);
                fullCondition.signal();
                lock.unlock();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
//        Log.e("code111", this.getClass().getName() + "  dispatchTouchEvent:" +event.getAction() + "");
        return super.dispatchTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.e("code111", this.getClass().getName() + " onTouchEvent:" +event.getAction() + "");
        return super.onTouchEvent(event);
    }

}
