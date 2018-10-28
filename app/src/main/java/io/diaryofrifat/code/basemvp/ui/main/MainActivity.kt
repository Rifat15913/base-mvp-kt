package io.diaryofrifat.code.basemvp.ui.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.diaryofrifat.code.basemvp.R
import io.diaryofrifat.code.basemvp.data.remote.retrophoto.RetroPhoto
import io.diaryofrifat.code.basemvp.databinding.ActivityMainBinding
import io.diaryofrifat.code.basemvp.ui.base.component.BaseActivity
import io.diaryofrifat.code.basemvp.ui.base.helper.LinearMarginItemDecoration
import io.diaryofrifat.code.utils.helper.ViewUtils
import io.diaryofrifat.code.utils.libs.ToastUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MainActivity : BaseActivity<MainMvpView, MainPresenter>(), MainMvpView {
    /**
     * Fields
     * */
    private lateinit var mBinding: ActivityMainBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun getActivityPresenter(): MainPresenter {
        return MainPresenter()
    }

    override fun getToolbarId(): Int? {
        return R.id.toolbar
    }

    override fun shouldShowBackIconAtToolbar(): Boolean {
        return false
    }

    override fun startUI() {
        mBinding = viewDataBinding as ActivityMainBinding

        ViewUtils.initializeRecyclerView(
                mBinding.recyclerViewPhotos,
                MainAdapter(),
                null,
                null,
                LinearLayoutManager(this),
                LinearMarginItemDecoration(ViewUtils.getPixel(R.dimen.margin_16)),
                null,
                DefaultItemAnimator()
        )

        presenter.compositeDisposable.add(getAdapter().dataChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Timber.d(it.toString())
                }, {
                    Timber.e(it)
                }))

        presenter.test()
    }

    private fun getRecyclerView(): RecyclerView {
        return mBinding.recyclerViewPhotos
    }

    private fun getAdapter(): MainAdapter {
        return getRecyclerView().adapter as MainAdapter
    }

    override fun onFetchingData(list: List<RetroPhoto>) {
        getAdapter().clear()
        getAdapter().addItems(list)
        ToastUtils.success("Successful")
        createNotificationChannel()
        testNotification()
    }

    override fun stopUI() {

    }

    fun testNotification() {
        val builder = NotificationCompat.Builder(this, "ChannelId")
                .setSmallIcon(R.drawable.abc_ic_star_half_black_48dp)
                .setContentTitle("Hello Notification")
                .setContentText("Hello content text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        manager.notify(1234, builder.build())
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Demo channel"
            val descriptionText = "Demo channel description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("ChannelId", name, importance).apply {
                description = descriptionText
            }

            val attributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()

            // Configure the notification channel.
            channel.setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/"
                    + R.raw.notification), attributes) // This is IMPORTANT

            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {

        }
    }
}
