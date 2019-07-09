

package com.lianer.supernest.manager;


import android.view.View;
import com.lianer.supernest.utils.SnackbarUtil;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * <pre>
 * Create by  :    L
 * Create Time:    2018-4-17
 * Brief Desc :
 * </pre>
 */
public class ScheduleCompat {

   public static <T> FlowableTransformer<T,T> apply(){
       return new FlowableTransformer<T, T>() {
           @Override
           public Publisher<T> apply(Flowable<T> upstream) {
               return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
           }
       };
   }

   public static void snackInMain(View view, String content){
       Flowable
               .just(content)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(s -> SnackbarUtil.DefaultSnackbar(view,s).show());
   }

}
