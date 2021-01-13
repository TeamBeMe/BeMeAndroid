package com.teambeme.beme.main.view

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.ToggleButton
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teambeme.beme.AlarmReceiver
import com.teambeme.beme.R
import com.teambeme.beme.base.BindingActivity
import com.teambeme.beme.databinding.ActivityMainBinding
import com.teambeme.beme.home.view.HomeFragment
import com.teambeme.beme.main.adapter.MainViewPagerAdapter
import com.teambeme.beme.main.viewmodel.MainViewModel
import com.teambeme.beme.util.StatusBarUtil
import java.util.*

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

    val pendingIntent = PendingIntent.getBroadcast(
        this, AlarmReceiver.NOTIFICATION_ID,  Intent(this, AlarmReceiver::class.java),
        PendingIntent.FLAG_UPDATE_CURRENT)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewPagerAdapter(this)
        LifeCycleEventLogger(javaClass.name).registerLogger(lifecycle)
        setBottomNavigationSelectListener(binding.bnvMain)

        setOntimePushAlarming()

    }

    private fun setBottomNavigationSelectListener(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_main_home -> {
                    binding.vpMain.currentItem = 0
                    StatusBarUtil.setStatusBar(this, Color.BLACK)
                    setViewPagerDefaultPosition()
                }
                R.id.menu_main_explore -> {
                    binding.vpMain.currentItem = 1
                    StatusBarUtil.setStatusBar(
                        this,
                        resources.getColor(R.color.explore_background_gray, null)
                    )
                }
                R.id.menu_main_following -> {
                    binding.vpMain.currentItem = 2
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
                R.id.menu_main_mypage -> {
                    binding.vpMain.currentItem = 3
                    StatusBarUtil.setStatusBar(this, resources.getColor(R.color.white, null))
                }
            }
            true
        }
    }

    private fun setViewPagerAdapter(fragmentActivity: FragmentActivity) {
        val viewPagerAdapter = MainViewPagerAdapter(fragmentActivity)
        binding.vpMain.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(PageChangeCallBack())
            requestDisallowInterceptTouchEvent(false)
        }
    }

    private fun setViewPagerDefaultPosition() {
        val homeFragment = supportFragmentManager.findFragmentByTag("f0") as HomeFragment
        homeFragment.returnToDefaultPosition()
    }

    private inner class PageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bnvMain.selectedItemId = when (position) {
                0 -> R.id.menu_main_home
                1 -> R.id.menu_main_explore
                2 -> R.id.menu_main_following
                3 -> R.id.menu_main_mypage
                else -> throw IllegalArgumentException("Wrong Position $position")
            }
        }
    }

//    private fun setAlarm(){
//        val receiverIntent = Intent(this@MainActivity, AlarmRecevier::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 0, receiverIntent, 0)
//        val from = "2021-01-10 17:1:00"
//
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//        var datetime: Date? = null
//        try {
//            datetime = dateFormat.parse(from)
//        } catch (e: ParseException) {
//            e.printStackTrace()
//        }
//        val calendar: Calendar = Calendar.getInstance()
//        calendar.setTime(datetime)
//        alarmManager!![AlarmManager.RTC, calendar.getTimeInMillis()] = pendingIntent
//    }

    private fun setOntimePushAlarming(){
        val repeatInterval: Long = 30 * 1000
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 22)
            set(Calendar.MINUTE, 0)
        }
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            repeatInterval,
            pendingIntent)

        Log.d("MainActivity", "OntimePush")
    }

}