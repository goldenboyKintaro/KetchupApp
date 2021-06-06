package com.moblie.ketchupapp.hilt

import android.content.Context
import androidx.room.Room
import com.moblie.ketchupapp.api.HQVideoService
import com.moblie.ketchupapp.api.MyDaddyService
import com.moblie.ketchupapp.api.MyDaddyStatusService
import com.moblie.ketchupapp.room.KetchupDatabase
import com.moblie.ketchupapp.utils.Environment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class SingletonModule {

    @Provides
    @Singleton
    fun database(@ApplicationContext appContext: Context): KetchupDatabase {
        return Room.databaseBuilder(
            appContext,
            KetchupDatabase::class.java, "lol.dp"
        ).fallbackToDestructiveMigration().build()
    }


    @Provides
    @Singleton
    fun jspoon(): JspoonConverterFactory =  JspoonConverterFactory.create()

    @Provides
    @Singleton
    fun apiService(jspoon: JspoonConverterFactory): HQVideoService =
        Retrofit.Builder()
            .baseUrl(Environment.BASE_URL)
            .addConverterFactory(jspoon)
            .build().create(HQVideoService::class.java)

    @Provides
    @Singleton
    fun accessService(jspoon: JspoonConverterFactory): MyDaddyStatusService =
        Retrofit.Builder()
            .baseUrl(Environment.STAT_URL)
            .addConverterFactory(jspoon)
            .build().create(MyDaddyStatusService::class.java)
    @Provides
    @Singleton
    fun myService(jspoon: JspoonConverterFactory): MyDaddyService =
        Retrofit.Builder()
            .baseUrl(Environment.MY_DADDY_URL)
            .addConverterFactory(jspoon)
            .build().create(MyDaddyService::class.java)

}